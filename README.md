# Mini excel proyect

>- Mini excel proyect base on [tsoding-miniExcel](https://www.youtube.com/watch?v=HCAgvKQDJng)
>- Attemp to replicate the proyect in java
>>- for now only work with the same operator in the line
>>- and can compute operations with 2 or more columns

## Example
>- with numbers
>>- executes the normal math operations
```console
.<A>.<B>.<C>.<D>
.<12>.<10>.<23>.<=A1+(C2*A2)>
.<1>.<2>.<18>.<=A2+B2>
.<12>.<10>.<15>.<=A3*(B3*C1)>
.<15>.<3>.<6>.<=A1/B2/B4>
.<>.<>.<>.<=A3-C4-A2>
```
### output 
>- expected output when using "()" to set priority
```console
<=C2*A2>=18
<=A1+(C2*A2)=>30
<=A2+B2>=3
<=B3*C1>=230
<=A3*(B3*C1)=>2760
<=A1/B2/B4>=2.0
<=A3-C4-A2>=5
```
-----

## Features 
- [x] numeric operation in lineal order
- [x] numeric operation between columns not matter the row
>>- `<12>-<10>-<=A4*B1>`

------

## Issues
>- for now when you have more than 2 columns to compute only works if the operation is the same for all the columns
```console
<12>-<10>-<20>-<=A1+B1+C1>
```
>>- if the operation is not the same it generates an error
```console
java.lang.NumberFormatException: For input string: "*"
```
>>- this is an attemp to compute more than 2 columns

------

## TODO's
- [ ] integrate the functionality to use the result of the operation to compute another operation
>>- `<C3>-<B1>-<=C3*B1>`
>>- `C3`: contains the operations so: `c3 = <A1*B1>`
>>- this means that if you use `c3` to compute the result of C3 with B1 this will generate an error because
>>>- C3 contains the operation not the result of the operation
