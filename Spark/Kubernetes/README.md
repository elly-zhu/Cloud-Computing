## PySpark on Kubernetes

1. Enable Kubernetes API
2. Create the cluster on GK

```
gcloud container clusters create spark \
--num-nodes=1 --machine-type=e2-highmem-2 --region=us-west3
```

3. Install the NFS Server Provisioner

```
helm repo add stable https://charts.helm.sh/stable
helm install nfs stable/nfs-server-provisioner \
--set persistence.enabled=true,persistence.size=5Gi
```

4. Create a persistent disk volume and a pod to use NFS spark-pvc.yaml

```
kubectl apply -f spark-pvc.yaml
```

5. Create and prepare the application jar file

```
docker run -v /tmp:/tmp -it bitnami/spark
-- find /opt/bitnami/spark/examples/jars/
-name 'spark-examples*' -exec cp {} /tmp/my.jar \;
```

6. Add a test file with a line of words that we will be using later for the word count test

```
echo "how much wood could a woodpecker chuck if a woodpecker
could chuck wood" > /tmp/test.txt

kubectl cp /tmp/my.jar spark-data-pod:/data/my.jar
kubectl cp /tmp/test.txt spark-data-pod:/data/test.txt
```

7. Copy the JAR file containing the application, and any other required files, to the PVC using the mount point，make sure the files a inside the persistent volume

```
kubectl exec -it spark-data-pod -- ls -al /data
```

8. Create spark-chart.yaml and deploy Apache Spark on Kubernetes
9. Deploy Apache Spark on the Kubernetes cluster using the Bitnami Apache Spark Helm chart and supply it with the configuration file above

```
helm repo add bitnami https://charts.bitnami.com/bitnami
helm install spark bitnami/spark -f spark-chart.yaml
```

10. Get the external IP of the running pod

```
kubectl get svc -l \
"app.kubernetes.io/instance=spark,app.kubernetes.io/name=spark"
```

11. Run Word Count on Spark

```
kubectl run --namespace default spark-client2 --rm --tty -i --restart=Never \
	--image=docker.io/bitnami/spark:3.4.1-debian-11-r0 \
	-- spark-submit --master spark://34.106.184.2:7077 \
	--deploy-mode cluster --class org.apache.spark.examples.JavaWordCount \
	/data/my.jar /data/test.txt
```

12. Find the output

```
kubectl get pods -o wide | grep {WORKER-NODE-ADDRESS} => kubectl get pods -o wide | grep 10.56.1.11

kubectl exec -it spark-worker-0 -- bash
cd /opt/bitnami/spark/work
cat driver-20230713..../stdout
```

13. Running python PageRank on PySpark on the pods

```
kubectl exec -it spark-master-0 -- bash
cd /opt/bitnami/spark/examples/src/main/python
spark-submit pagerank.py /opt 2
```
