import matplotlib.pyplot as plt
import numpy as np

x = np.arange(8, 17) #横坐标
file1 = open('data/n_thread.txt', "r")
y1 = []
list1 = file1.readline().split(',')
for i in list1:
    y1.append(int(i))
file2 = open('data/single_thread.txt','r')
y2 = []
list2 = file2.readline().split(',')
for i in list2:
    y2.append(int(i))
#fig = plt.figure(num=1, figsize=(15, 8),dpi=80)
plt.rcParams['font.sans-serif']=['SimHei'] #用来正常显示中文标签
plt.title('单线程和多线程运行时间对比')
plt.ylabel('运行时间(单位: ms)')
plt.xlabel('棋盘的大小')
plt.plot(x, y2, label='single_thread')
plt.plot(x, y1, label='N_thread')
plt.legend(loc='upper left')
plt.show()