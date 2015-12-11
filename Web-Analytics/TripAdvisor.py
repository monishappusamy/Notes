# -*- coding: utf-8 -*-
"""
Created on Wed Dec  9 15:08:17 2015

@author: Monish
"""
from selenium import webdriver
import re

fileReader = open("haveto_Monish.txt")

for line in fileReader:
    line = line.strip()
    hotelName, url = line.split('\t')
    i=7
    n=1000

    while i<=n:
        current_url = url + str(i)
        filename = "page%i.html" % i
        filename = "/Users/Monish/Desktop/660/Extra_Project/" + hotelName + "/" + filename
        print filename
        fw = open(filename,'w')
        driver = webdriver.Chrome('./chromedriver')
        driver.get(current_url)
        print current_url
        html = driver.page_source
        for line in html:
            try:
                fw.write(line)
            except:
                continue
        print 'page %i done' %i
        if n == 1000:
            totals=re.finditer('<a class="more taLnk" href="#REVIEWS">(.*?) Reviews</a>',html)    
            for total in totals:
                n = int(total.group(1).strip()) - 6
        i += 6
        driver.close()
        fw.close()
