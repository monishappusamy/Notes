# -*- coding: utf-8 -*-
# Author: Monish Kumar Appusamy (CWID:10406741)

# Exercise 09 - Program to process MovieLens movie rating data files using Pandas

# -*- coding: utf-8 -*-
import pandas as pd

# prepare column name for "users" data frame
unames = ['user_id', 'gender', 'age', 'occupation', 'zip'] 
# Import "users.dat" file into "users" data frame
users = pd.read_table('users.dat', sep='::', header=None, names=unames, engine = 'python')

# prepare column name for "ratings" data frame
rnames = ['user_id', 'movie_id', 'rating', 'timestamp'] 
# Import "ratings.dat" file into "ratings" data frame
ratings = pd.read_table('ratings.dat', sep='::', header=None, names=rnames, engine = 'python')

# prepare column name for "movies" data frame
mnames = ['movie_id', 'title', 'genres'] 
# Import "users.dat" file into "movies" data frame
movies = pd.read_table('movies.dat', sep='::', header=None, names=mnames, engine = 'python')

# merge "users", "ratings", "movies" dataframe into one dataframe "data"
data = pd.merge(pd.merge(ratings,users), movies)

# get average ratings by gender with pivot table
mean_ratings = data.pivot_table('rating', index='title', columns='gender', aggfunc='mean')

# group ratings by tile and get size of each group
ratings_by_title = data.groupby('title').size()

# create series of titles with >=150 ratings, to be used as index
active_ratings = ratings_by_title.index[ratings_by_title >= 150]

# use active_ratings series as index for mean_ratings
new_mean_ratings = mean_ratings.ix[active_ratings]

# sorted version of the mean_ratings dataframe, sorted in descending order by female rating
top_female_ratings = new_mean_ratings.sort_values(by='F', ascending=False)

# print Female Top 10 list 
print '\nFemale Top 10:' 
print top_female_ratings[:10]

# sorted version of the mean_ratings dataframe, sorted in descending order by male rating
top_male_ratings = new_mean_ratings.sort_values(by='M', ascending=False)

# print Male Top 10 list 
print '\nMale Top 10:' 
print top_male_ratings[:10]
