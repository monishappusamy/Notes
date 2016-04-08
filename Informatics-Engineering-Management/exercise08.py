# Author: Monish Kumar Appusamy (CWID:10406741)

# Exercise 08-This program reads the GEDCOM file "StevensFamily.txt"  and prints information about each family member.

# Get file name from user
fileName = raw_input()

# Open the file
file = open(fileName)

# initialize variables
BirthOrDeath = 'NULL'
SEX = 'NULL'

# format's date into MMM DD, YYYY format
def formatdate(partslist):
    return partslist[1] + ' ' + partslist[0] + ', ' + partslist[2]

# determine's correct pronoun 'He' or 'She' 
def MaleOrFemale(partslist):
    if partslist == 'M':
        return 'He'
    elif partslist == 'F':
        return 'She'

# Maps code with correct family name
def family(partslist):
    if partslist == '@F01@':
        return 'Stevens-Picton family'
    elif partslist == '@F02@':
        return 'Stevens-Dod family'
    else:
        return partslist

# loops through the given file line by line
for line in file:
    # split a line and store as a list
    parts = line.strip().split()
    
    # print name
    if parts[1] == 'NAME':
        print ''
        print ' '.join(parts [2:]) + ':'
    
    # defines He or She    
    if parts[1] == 'SEX':
        SEX = MaleOrFemale(parts[2])
    
    # Set BirthOrDeath variable
    if parts[1] == 'BIRT':
        BirthOrDeath = 'was born'
    elif parts[1] == 'DEAT':
        BirthOrDeath = 'died'
    
    # print date in appropriate format    
    if parts[1] == 'DATE':
        print SEX + ' ' + BirthOrDeath + ' on ' + formatdate(parts[2:]) + '.'
    
    # print spouse information 
    if parts[1] == 'FAMS':
        print SEX + ' ' + 'is a spouse in the ' + family(parts[2]) + '.'
    
    # print child information 
    if parts[1] == 'FAMC':
        print SEX + ' ' + 'is a child in the ' + family(parts[2]) + '.'
        
