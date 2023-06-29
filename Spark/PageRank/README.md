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
![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9e57f4b3-9c6a-4465-a919-95c93a71576f/Untitled.png)  
![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/3e43458a-7383-4ee0-bfb4-df34b3888a9f/Untitled.png)  
![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/cc44c65c-8f47-4cad-8445-752ce5737072/Untitled.png)  
Dataset Preparation:  
pagerank_input.txt  
A C  
A B  
C A  
B C  
![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b8a3b0af-96ab-485b-b13c-5ae721f0d209/Untitled.png)  
![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/25e1ed84-4f64-4c22-89cc-299791809b93/Untitled.png)  
#### Execution
##### PySpark

##### Scala
#### Conclusion
#### Others
