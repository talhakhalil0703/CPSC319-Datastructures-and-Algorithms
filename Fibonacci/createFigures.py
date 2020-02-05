import csv
import matplotlib.pyplot as plt
import numpy as np
from scipy.signal import medfilt
from scipy.optimize import curve_fit

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

    print(algType+ ": " + str(mean+2*std) + " to " +str(mean-2*std))
    
    newTimes = []
    newIndex = []

    for i, item in enumerate(times):
        if (item > (mean - 2*std) and item < (mean + 2*std)):
            newTimes.append(item)
            newIndex.append(n[i])
        # else:
        #    print("Tossing Value")

    if (filter):
         newTimes = medfilt(newTimes)
    
    values = curve_fit(linear,newIndex, newTimes)

    # print("Curve fitting values: ")
    # print(values)
    plt.plot(newIndex, newTimes, color = "red")
    plt.plot(newIndex, linear(newIndex, *values))
    plt.xlabel("Nth Fibonacci Sequence")
    plt.ylabel("Time taken to run the Nth Fibonacci Sequence in nanoseconds")
    plt.title( algType + " Fibonacci Analysis")
    
    plt.savefig(algType)
    plt.show()

def linear(x ,a ,b):
    return a * x + b

createFigures("recursive", False)
createFigures("memoization", True)
createFigures("dynamic", True)
createFigures("iterator", True)
createFigures("matrix", True)