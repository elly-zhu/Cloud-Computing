## Spark
### PageRank
#### Question
![image](https://github.com/elly-zhu/Cloud-Computing/assets/22209839/290cd074-5319-42c3-aae9-29a03bc4b640)
#### Design Approach
The initial PageRank value for each webpage is 1 and damping factor is 0.85
PR(A) = 1  
PR(B) = 1  
PR(C) = 1  
Page A has a link to pages B,C  
Page B has a link to page C  
Page C has links to page A  

PR(A) = (1-d) + d * (PR(C)/1)  
PR(B) = (1-d) + d * (PR(A)/2)  
PR(C) = (1-d) + d * (PR(B)/1 + PR(A)/2)  


- A web page does not have any input will have
  - constant PageRank: 1-d
  - the smallest PageRank
- Input Web Pages' impact to the PageRank of a web page
  - The more Input Web Pages the better.
  - The higher PageRank of an Input Web Page the better.

#### Preparation
Environment: 
GCP, Ubuntu VM, Dataproc, Spark 
![image](https://github.com/elly-zhu/Cloud-Computing/assets/22209839/bdb5be72-945d-49f8-b062-c7f1d34a054f)
![image](https://github.com/elly-zhu/Cloud-Computing/assets/22209839/9ec68005-a2a5-477a-a4b0-a389882adc0d)
Dataset Preparation:  
pagerank_input.txt  
A C  
A B  
C A  
B C  
![image](https://github.com/elly-zhu/Cloud-Computing/assets/22209839/3ac29e84-8e36-4e4c-a419-323d46f7bebf)
![image](https://github.com/elly-zhu/Cloud-Computing/assets/22209839/77765432-e3c0-4f27-99db-b30073298672)

#### Execution
##### PySpark
Create the pagerank.py file  
![image](https://github.com/elly-zhu/Cloud-Computing/assets/22209839/4d754b7d-123b-43a0-9018-71e676ec78b6)  
Run the pagerank.py from the cloud shell terminal with the input file in the bucket  

**Set iteration = 1**  
Run the command in Cloud Shell with argument iteration = 1  
```
gcloud dataproc jobs submit pyspark --cluster cluster-7e47 --region us-central1 pagerank.py -- gs://pagerank_ezhu9249/pagerank_input.txt 1
```  

![image](https://github.com/elly-zhu/Cloud-Computing/assets/22209839/c1373faf-9122-4e14-b1fe-97d9fc90da70)

**Get Result (iteration = 1)**  
 - A has rank: 1.0. 
 - B has rank: 0.575.  
 - C has rank: 1.4249999999999998.

**Set iteration = 2**  

Rerun the command in Cloud Shell with argument iteration = 2  
```
gcloud dataproc jobs submit pyspark --cluster cluster-7e47 --region us-central1 pagerank.py -- gs://pagerank_ezhu9249/pagerank_input.txt 2
```  

![image](https://github.com/elly-zhu/Cloud-Computing/assets/22209839/e093f650-a5d1-47d1-a2b7-40ee56ffd485)

**Get Result (iteration = 2)**  
 - A has rank: 1.3612499999999996.
 - B has rank: 0.575.
 - C has rank: 1.06375.

##### Scala
Create a my-scala folder and create pagerank. scala inside the folder  
![image](https://github.com/elly-zhu/Cloud-Computing/assets/22209839/f72125db-6cba-4b8e-93af-cba3372c6c30)  

If no sbt is installed, install sbt first  
![image](https://github.com/elly-zhu/Cloud-Computing/assets/22209839/b1c76dd5-ac4c-474c-9b3b-2b4456ed4279)  

Compile the scala file to produce jar file  
![image](https://github.com/elly-zhu/Cloud-Computing/assets/22209839/4882ec5b-f91a-4c28-b1bf-ab4fa8959aab)  

Copy the sparkpagerank_2.12-1.0.jar to the bucket  
Run the job submit in Cloud Shell with the same input file and set argument iteration = 1   
**Set iteration = 1**  
```
gcloud dataproc jobs submit spark \
    --cluster cluster-7e47 \
    --region us-central1 \
    --class org.apache.spark.examples.SparkPageRank \
    --jars gs://pagerank_ezhu9249/sparkpagerank_2.12-1.0.jar \
    -- \
    gs://pagerank_ezhu9249/pagerank_input.txt 1
```

![image](https://github.com/elly-zhu/Cloud-Computing/assets/22209839/695ef01f-a2aa-4cd5-a68b-ee2bc4c62ca9)
 
**Get Result (iteration = 1)**  
 - A has rank: 1.0.
 - B has rank: 0.575.
 - C has rank: 1.4249999999999998.

 **Set iteration = 2**  
 ```
gcloud dataproc jobs submit spark \
    --cluster cluster-7e47 \
    --region us-central1 \
    --class org.apache.spark.examples.SparkPageRank \
    --jars gs://pagerank_ezhu9249/sparkpagerank_2.12-1.0.jar \
    -- \
    gs://pagerank_ezhu9249/pagerank_input.txt 2
```
![image](https://github.com/elly-zhu/Cloud-Computing/assets/22209839/bf5f63ed-77d3-4c08-b74b-3648a05f8a93)

**Get Result (iteration = 2)**  
 - A has rank: 1.3612499999999996.
 - B has rank: 0.575.
 - C has rank: 1.06375.

#### Others
