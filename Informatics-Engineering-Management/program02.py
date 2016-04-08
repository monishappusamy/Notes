# Author: Monish Kumar Appusamy (CWID:10406741)

# Exercise 08 - This program reads CityBike dataset and tries to predict whether 
# Citi Bike riders take longer bike trips on weekdays or on weekends?

import csv, datetime, matplotlib.pyplot as plot

# open data file
file = open('CitiBike_F15.csv', 'r')

# Python's csv data reader is used to process through the csv file
datareader = csv.reader(file)

# initializing variables and lists
previous_miles = 0
miles_travelled_today = 0
today_average_tripLen = 0
average_tripLen = []
weekday_tripLen = []
weekend_tripLen = []
tripLength=[]

# takes date as string input and returns true if it is a week day or false if it is weekend
def is_weekday(indate):
    dateparts = indate.split('/')
    year = int(dateparts[2])
    month = int(dateparts[0])
    day = int(dateparts[1])
    dayofweek = datetime.date(year,month,day).weekday()
    if dayofweek == 5:
        return False
    elif dayofweek == 6:
        return False
    else:
        return True

# calculates average for given list
def average(average_tripLen):
    return sum(average_tripLen)/float(len(average_tripLen))

# loops through the file line by line
for line in datareader:
    if 'Date' in line:
        continue
    else:
        if previous_miles == 0:
            previous_miles = float(line[4])
        else:
            # subtract todays Miles travelled to date with previous days Miles travelled to date record to obtain miles traveled today value
            miles_travelled_today = float(line[4]) - previous_miles
            
            # calculate average of trip length
            today_average_tripLen = miles_travelled_today / float(line[1])
            
            # append the average calculated to average trip length list
            average_tripLen.append(today_average_tripLen)
            
            # if it is a weekday append the average calculated to weekday list or else to weekend list
            if(is_weekday(line[0])):
                weekday_tripLen.append(today_average_tripLen)
            else:
                weekend_tripLen.append(today_average_tripLen)
            previous_miles = float(line[4])

# calculate daily average
daily = average(average_tripLen)

# calculate weekday average
weekday = average(weekday_tripLen)

# calculate weekend average
weekend = average(weekend_tripLen)

# print the calculate results
print 'daily', daily
print 'weekday', weekday
print 'weekend', weekend

# plot line graph to show the bike trip length per day
plot.subplot(221)                      
plot.plot(average_tripLen)                    
plot.ylabel("Miles")                           
plot.xlabel("Days") 
plot.title('Average bike trip length per day') 

# plot bar graph to show the average of daily vs weekday vs weekend
plot.subplot(222)                                             
plot.bar([0, 1, 2],[daily, weekday, weekend], color='blue', alpha=0.5)  
y_pos = [ .5, 1.5, 2.5 ]                                             
labels = ["All days", "Weekday", "Weekend"]                            
plot.xticks(y_pos, labels)     
plot.ylabel("Miles")                                       
plot.xlabel("Average Length of Trip")
plot.title('Average Bike trip length (All days vs Weekday vs Weekend)') 

plot.show()
