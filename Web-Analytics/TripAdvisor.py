# -*- coding: utf-8 -*-
"""
Created on Wed Dec  9 15:08:17 2015

@author: Monish
"""
from selenium import webdriver
import re

i=7
n=1000
while i<=n:
    url = "http://www.tripadvisor.com/ShowUserReviews-g34438-d87137-r331479907-The_Mutiny_Hotel-Miami_Florida.html#or%i" %i
    fileName = "page%i.html" % i
    fw = open(fileName,'w')
    driver = webdriver.Chrome('./chromedriver')
    driver.get(url)
    html = driver.page_source
    for line in html:
        try:
            fw.write(line)
        except:
            #print 'exception'
            continue
    print 'page %i done' %i
    if n == 1000:
        totals=re.finditer('<a class="more taLnk" href="#REVIEWS">(.*?) Reviews</a>',html)    
        for total in totals:
            n = int(total.group(1).strip()) - 6
    i += 6
    driver.close()
    fw.close()
