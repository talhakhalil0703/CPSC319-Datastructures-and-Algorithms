import csv
import matplotlib.pyplot as plt
import numpy as np
from scipy.signal import medfilt

def createFigures(algType, filter):
    n = []
    times = []
    with open(algType+ ".csv", mode = 'r') as csv_file:
        csv_reader = csv.DictReader(csv_file)

        for row in csv_reader:
            n.append(float(row["n"]))
            times.append(float(row["times"]))
    n = np.array(n)
    times = np.array(times)
    mean = np.mean(times)
    std = np.std(times)

    print(algType+ ": " + str(mean+2*std) + " - " +str(mean-2*std))
    
    newTimes = []
    newIndex = []

    for i, item in enumerate(times):
        if (item > (mean - 2*std) and item < (mean + 2*std)):
            newTimes.append(item)
            newIndex.append(n[i])
        else:
           print("Tossing Value")

    if (filter):
         newTimes = medfilt(newTimes)
    
 
    plt.plot(newIndex, newTimes, color = "red")
    plt.scatter(newIndex, newTimes)
    plt.xlabel("Nth Fibonacci Sequence")
    plt.ylabel("Time taken to run the Nth Fibonacci Sequence in nanoseconds")
    plt.title( algType + " Fibonacci Analysis")
    plt.show()
    plt.savefig(algType + ".jpg")

createFigures("recursive", False)
createFigures("memoization", True)
createFigures("dynamic", True)
createFigures("iterator", True)
createFigures("matrix", True)