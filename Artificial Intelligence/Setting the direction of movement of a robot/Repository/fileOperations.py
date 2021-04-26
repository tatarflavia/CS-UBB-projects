import csv


def readTrainingData():
    # reads from the file
    # and returns a tuple made up of an array of arrays with sensors from every row and the output/direction
    trainingData = []
    output = []
    with open('sensor_readings_24.data') as csv_file:
        for row in csv.reader(csv_file, delimiter=','):
            trainingData.append([float(row[i]) for i in range(24)])
            output.append(row[24])
    return trainingData, output


def readTestData():
    # same thing,only for the test data
    # returns an array of arrays made up of sensors data from every row
    testingData = []
    output = []
    with open('test.in') as csv_file:
        for row in csv.reader(csv_file, delimiter=','):
            testingData.append([float(row[i]) for i in range(24)])
            output.append(row[24])
    return testingData