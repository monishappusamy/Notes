@author: Monish
"""
from selenium import webdriver

i=1
while i <= 289:
    url = "http://www.tripadvisor.com/ShowUserReviews-g34438-d557002-r330616724-Leamington_Hotel-Miami_Florida.html#or%i" %i
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
    i += 6
    driver.close()
    fw.close()
