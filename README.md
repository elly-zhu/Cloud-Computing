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
