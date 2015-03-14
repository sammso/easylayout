#  EasyLayout 1.0

EasyLayout is flexible and easy to use grid based 
LayoutManager. EasyLayout is designed for making form design 
easier and quicker without need of fancy drag and drop form 
painters. 
  
The idea int EasyLayout is to use grid to layout components.
The grid is based on columns and rows, for each row has to 
be define size and resizing values. Size can be read 
automatically from  components or it can be defined by 
pixel values. Resizing is defined by percentage values 
for each column and row. 

#
  
Grid of example application.

```  
      0      1      2
    +--0%--+-100%-+--0%--+
  0 0%     |      |      |
    +------+------+------+
  1 0%     |      |      |
    +------+------+------+
  2 50%    |      |      |
    +------+------+------+
  3 50%    |      |      |
    +------+------+------+
```
  
To run example application

```
  java -jar testapp.jar on /libs directory
```
  
## Changes in 1.1
  
Rename Constraint class to Position claass and new Constraint class created for compatiblity reasons
  
======

Licence: Common Public License - v 1.0. See cpl10.txt
  
