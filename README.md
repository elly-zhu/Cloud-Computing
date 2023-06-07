# Cloud-Computing
## MapReduce
### Pi
#### Design Approach
![image](https://github.com/elly-zhu/Cloud-Computing/assets/22209839/1a301e8f-1d7a-4ab4-8039-2de89d963fcf)
We would need a large randomized dataset, and by summing the number of points inside and outside the circle, we could estimate pi using the formula 4(S/N)

#### Preparation
Environment: 
GCP, Ubuntu VM, MapReduce
Dataset Preparation:
GenerateRandomCoordinates.java
100,000 random coordinates with radius (r) = 100

#### Execution
![image](https://github.com/elly-zhu/Cloud-Computing/assets/22209839/7d599f2e-4efe-4eca-87d4-fcf82fdb0fcb)
![image](https://github.com/elly-zhu/Cloud-Computing/assets/22209839/9da3649a-1e61-4d51-a885-3f7163b9868b)
![image](https://github.com/elly-zhu/Cloud-Computing/assets/22209839/e2dc79d5-5a68-4da9-8224-c1b636187283)
100,000 random coordinates generated with radius=100

Calculation Result:

Inside 785817

Outside 214183

PI: 3.143268

#### Conclusion
This project successfully utilized the MapReduce framework to estimate the value of pi by generating 100,000 random coordinates with a radius of 100. By employing the Monte Carlo method, we were able to approximate pi with an estimate of 3.143268. This demonstrates the effectiveness of the MapReduce paradigm in handling large-scale data processing tasks efficiently.

The accuracy of our pi estimate could be further improved by increasing the number of random coordinates generated or adjusting the radius.

The exploration of pi calculation using MapReduce not only provided practical insights into big data processing but also showcased the power of parallel processing and probabilistic algorithms.

#### Others
Google Slide: https://docs.google.com/presentation/d/1X2jd1o2m72zHpJB-P4_OazOF9SpCMhLfK3sdSEmh-EE/edit?usp=sharing
----
### Full Inverted Index
#### Design Approach
![image](https://github.com/elly-zhu/Cloud-Computing/assets/22209839/6bc9cd51-4462-44c3-b2be-60d3ca5ba3c3)
The design approach for implementing a full inverted index involves utilizing the MapReduce framework to process the input text documents, extract unique words, and associate them with their respective coordinates (file index and word index) to enable efficient searching and analysis of the document collection.

#### Preparation
Environment: 
GCP, Ubuntu VM, MapReduce
Dataset Preparation:
- Text files:
 - File0.txt: it is what it is
 - File1.txt: what is it 
 - File2.txt: it is a banana


#### Execution
![image](https://github.com/elly-zhu/Cloud-Computing/assets/22209839/b6909ee7-644a-4338-9641-a7255d04bd87)
![image](https://github.com/elly-zhu/Cloud-Computing/assets/22209839/24a56052-2a61-475e-90e8-d039049c1414)
![image](https://github.com/elly-zhu/Cloud-Computing/assets/22209839/c81a42ed-4d19-438e-a197-bddc19a805b0)


Calculation Result:

a       (2, 2)

banana  (2, 3)

is      (2, 1),(0, 4),(0, 1),(1, 1)

it      (2, 0),(0, 3),(0, 0),(1, 2)

what    (0, 2),(1, 0)

#### Conclusion
In conclusion, the successful implementation of the inverted index using MapReduce showcases the ability to process large volumes of textual data and generate meaningful representations for efficient information retrieval. 

The program's ability to map words to their coordinates, reflecting the file index and word position, allows for quick and accurate searching of specific terms within the document collection.

This application has diverse potential uses, including document clustering, content recommendation, and keyword-based analysis. By harnessing the power of distributed computing and the MapReduce framework, we can unlock the full potential of big data analysis and gain valuable insights from large-scale text datasets.


#### Others
Google Slide: https://docs.google.com/presentation/d/1A-2AjmcEzK4fH8vMTpuyqi0yZ9kBgwpD20bDkNtLRyQ/edit?usp=sharing
