# -*- coding: utf-8 -*-
"""
Created on Mon Oct  5 14:29:56 2015

@author: Monish
"""
 
import time, re, sys
from selenium import webdriver

fw = open('reviews.txt','w')

def parsePage(html):
    review_list = []
    rating_list = []
    date_list = []
    
    reviews = re.finditer("<span itemprop=\"description\">(.*?)</span>", html)
    for review in reviews:
        review_list.append(review.group(1).strip())
        
    ratings = re.finditer("<span property=\"v:value\" class=\"BVRRNumber BVRRRatingNumber\">(.*?)</span>", html)
    for rating in ratings:
        rating_list.append(rating.group(1).strip())
        
    dates = re.finditer("class=\"BVRRValue BVRRReviewDate\">(.*?)<span property=\"v:value-title\"></span></span>", html)    
    for date in dates:
        date_list.append(date.group(1).strip())
    
    for i in range(0, len(date_list)):
        fw.write('bestbuy.com' + '\t' + review_list[i]+ '\t' + rating_list[i] + '\t' + date_list[i] + '\n')
    

url = "http://www.bestbuy.com/site/insignia-32-class-31-1-2-diag--led-720p-hdtv-black/6080010.p?id=1219191179593&skuId=6080010"

driver = webdriver.Chrome('./chromedriver')
driver.get(url)
time.sleep(2)

button=driver.find_element_by_css_selector('#ui-id-3')
button.click() 

time.sleep(2)

parsePage(driver.page_source)
print 'page 1 done'

page=2
while True:
    #get the css path of the 'next' button
    cssPath='#BVRRDisplayContentFooterID > div > span.BVRRPageLink.BVRRNextPage > a'
    
    try:
        button=driver.find_element_by_css_selector(cssPath)
    except:
        error_type, error_obj, error_info = sys.exc_info()
        print 'STOPPING - COULD NOT FIND THE LINK TO PAGE: ', page
        print error_type, 'Line:', error_info.tb_lineno
        break

    #click the button to go the next page, then sleep    
    button.click()
    time.sleep(2)
    
    #parse the page
    parsePage(driver.page_source)

    print 'page',page,'done'
    page+=1

fw.close()