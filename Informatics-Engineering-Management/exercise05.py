#  Author:  Monish Kumar Appusamy

# Exercise 5

def plot_income(income_numbers):
    # numpy and matplotlib needed for plot_income function
    import numpy as np
    import matplotlib.pyplot as plt

    # this function plots bachelors vs grad education for low, med, high income
    # income_numbers is a list of lists, format [ [lb, mb, hb] , [lg, mg, hg] ]

    n_groups = 3  # 3 inncome levels
    fig, ax = plt.subplots()
    index = np.arange(n_groups)
    bar_width = 0.35
    
    # income[0] = list for bachelors income numbers [low, med, high]
    rects1 = plt.bar(index, income_numbers[0], bar_width, color='b')
    # income[1] = list for graduate income numbers [low, med, high]
    rects2 = plt.bar(index + bar_width, income_numbers[1], bar_width, color='g')
 
    plt.title('Income: Bachelors vs. Grad School')
    plt.xticks(index + bar_width, ('Low', 'Med', 'High'))
 
    plt.show()
    
# helper method to return index number (Ex. response 1-4 returns 0, Income response 5-8 returns 1, Income response 9 returns 2)
def get_index(temp):
    num = int(temp)
    if num >=1 and num <=4:
        return 0
    elif num >= 5 and num <= 8:
        return 1
    elif num == 9:
        return 2

# income_counts[][] matrix will contain the final chart co-ordinates
income_counts = [[0,0,0],[0,0,0]]
# education_index is to store index of education (Ex. 0 for bachelors, 1 for graduate study)
education_index = 0
# income_level_index is to stode index of income level (Ex. 0 for low, 1 for medium, 2 for high)
income_level_index = 0
file = open('Marketingdata.txt', 'r')
parts = []
for line in file:
    if 'NA' in line:
        continue
    else:
        # split the line and store it as list (Ex. ['6', '1', '5', '3', '4', '1', '5', '1', '1', '0', '2', '3', '5', '1'])
        parts = line.split()
        # if education is bachelors
        if int(parts[4]) == 5:
            education_index = 0
            income_level_index = get_index(parts[0])
            # increment matrix count by 1 for bachelors and relevant income level
            income_counts[education_index][income_level_index] += 1 
        # if education is graduate study
        elif int(parts[4]) == 6:
            education_index = 1
            income_level_index = get_index(parts[0])
             # increment matrix count by 1 for graduate study and relevant income level
            income_counts[education_index][income_level_index] += 1 
            
plot_income(income_counts)
