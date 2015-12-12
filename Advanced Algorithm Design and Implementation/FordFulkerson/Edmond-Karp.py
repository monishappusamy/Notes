# -*- coding: utf-8 -*-
"""
Created on Sat Dec  5 17:01:49 2015

@author: Monish
"""

import decimal
import sys
import time
start_time = time.time()

count = 0

def EdmondsKarp(capacity, neighbors, start, end):
  flow = 0
  length = len(capacity)
  flows = [[0 for i in range(length)] for j in range(length)]
  #count=0
  while True:
    max, parent = BFS(capacity, neighbors, flows, start, end)
    #count=count+1
    print 'max:%s' % max
    print parent
    if max == 0:
      break
    flow = flow + max
    v = end
    while v != start:
      u = parent[v]
      flows[u][v] = flows[u][v] + max
      flows[v][u] = flows[v][u] - max
      v = u
  return (flow, flows)


def BFS(capacity, neighbors, flows, start, end):
  global count
  length = len(capacity)
  parents = [-1 for i in xrange(length)] # parent table
  parents[start] = -2 # make sure source is not rediscovered
  M = [0 for i in xrange(length)] # Capacity of path to vertex i
  M[start] = decimal.Decimal('Infinity') # this is necessary!

  queue = []
  queue.append(start)
  while queue:
    u = queue.pop(0)
    for v in neighbors[u]:
      count = count+ 1
      # if there is available capacity and v is is not seen before in search
      if capacity[u][v] - flows[u][v] > 0 and parents[v] == -1:
        parents[v] = u
        # it will work because at the beginning M[u] is Infinity
        M[v] = min(M[u], capacity[u][v] - flows[u][v]) # try to get smallest
        if v != end:
          queue.append(v)
        else:
          return M[end], parents
  return 0, parents


def ParseGraph(file):
  file_object = open(file, "r")
  capacity = []
  neighbors = {} # neighbors include reverse direction neighbors
  for line in file_object.readlines():
    capacity.append([int(i) for i in line.split(',')])
  for vertex in xrange(len(capacity)):
    neighbors[vertex] = []
  for vertex, flows in enumerate(capacity):
    for neighbor, flow in enumerate(flows):
      if flow > 0:
        neighbors[vertex].append(neighbor)
        neighbors[neighbor].append(vertex) # reverse path may be used
  return capacity, neighbors


if __name__ == "__main__":
  file_name = "/Users/Monish/Desktop/600/flow_network.txt" # use file flow_network.txt
  capacity, neighbors = ParseGraph(file_name)
  for line in capacity:
    print line
  print neighbors
  flow, flows = EdmondsKarp(capacity, neighbors, 0, 6)
  print 'Edmond Karp Algorithm'
  print 'Max flow: %s' % flow
  '''
  print 'Flow matrix:'
  for line in flows:
    print line
  '''  
  print("Total Time--- %s seconds ---" % (time.time() - start_time))
