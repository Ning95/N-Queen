# 利用多线程解决 N 皇后问题

## ✏️项目介绍

- n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击，是回溯算法的典型案例
- 由于算法的时间复杂度过高[O(n!)]，导致 n 值过高时，运行特别耗时。一般我们都是采用单线程来解决此类问题，因此我们希望发挥多核 CPU 的优势，考虑使用多线程解决 N 皇后问题，提高运行的效率

## 🎨结果展示

#### 单线程输出结果

> 解决 8皇后问题，用时：5毫秒，计算结果：92
> 解决 9皇后问题，用时：3毫秒，计算结果：352
> 解决 10皇后问题，用时：8毫秒，计算结果：724
> 解决 11皇后问题，用时：9毫秒，计算结果：2680
> 解决 12皇后问题，用时：48毫秒，计算结果：14200
> 解决 13皇后问题，用时：191毫秒，计算结果：73712
> 解决 14皇后问题，用时：1047毫秒，计算结果：365596
> 解决 15皇后问题，用时：6993毫秒，计算结果：2279184
> 解决 16皇后问题，用时：46604毫秒，计算结果：14772512

#### 多线程输出结果

> 解决 8 皇后问题，用时：3毫秒，计算结果：92
> 解决 9 皇后问题，用时：3毫秒，计算结果：352
> 解决 10 皇后问题，用时：8毫秒，计算结果：724
> 解决 11 皇后问题，用时：33毫秒，计算结果：2680
> 解决 12 皇后问题，用时：205毫秒，计算结果：14200
> 解决 13 皇后问题，用时：1038毫秒，计算结果：73712
> 解决 14 皇后问题，用时：5610毫秒，计算结果：365596
> 解决 15 皇后问题，用时：38108毫秒，计算结果：2279184
> 解决 16 皇后问题，用时：283734毫秒，计算结果：14772512

#### 利用 Python 可视化

![]()

## 💡待拓展

- 一台服务器的 CPU 核数是有限制的, 可以利用分布式的思想在多台服务器上运行
