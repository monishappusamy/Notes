"""
@author: Monish Kumar Appusamy

Problem description:
Your script should read a document from a file called "in.txt". It should then 
find all 3-grams that: 

1. include at least 2 nouns 
2. do not include any stopwords 

The 3grams should be written in a file called "out.txt". Write one 3gram per line with a space 
between the terms. For example: 

new york city 
not very good 

1. Use the dot as a sentence splitter 
2. Use nltk's list of stopwords 
3. Don't forget to lower case the document and remove non-letter characters. 
4. The 3grams should be written in the order that they appear in the document (based on their first occurrence)

The script includes the following pre-processing steps for text:
- Ngrams
- POS tagging
"""

import nltk.data
from nltk.util import ngrams
from nltk.corpus import stopwords
import re


#read the input
f=open('in.txt')
text=f.read().strip().lower()
f.close()


#split sentences
sentences=re.split('\.',text)
print 'NUMBER OF SENTENCES: '+ str(len(sentences))

#initialize the output file
fileWriter = open('out.txt', 'w')

#all the stopwords
stops = set(stopwords.words('english'))

# for each sentence
for sentence in sentences:
    
	#set to store all the nouns in the given text
    noun = set()
    
    sentence=re.sub('[^a-z\d]',' ',sentence)#replace chars that are not letters or numbers with a space
    sentence=re.sub(' +',' ',sentence).strip()#remove duplicate spaces

    #tokenize the sentence
    terms = sentence.split()
    
    tagged_terms=nltk.pos_tag(terms)#do POS tagging on the tokenized sentence eg. 'York', 'NN'
    
    
    
    for pair in tagged_terms: 
        
        #if the word is a Noun
        if pair[1].startswith('NN'): noun.add(pair[0])
           
    threegrams = ngrams(terms,3) 

    for tg in threegrams:
        temp = ''    
        c = 0 #counter to check the atleast 2 nouns condition
        for num in range(0,3):
            if tg[num] in noun :
                c+=1
		
		#if there is atleast 2 nouns and there are no stopwords		
        if c>=2 and (tg[0] not in stops) and (tg[1] not in stops) and (tg[2] not in stops):
            temp = tg[0] + ' ' + tg[1] + ' ' + tg[2] + '\n'
            fileWriter.write(temp)

fileWriter.close()
    
