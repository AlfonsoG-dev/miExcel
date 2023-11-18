# Mini excel proyect

>- Mini Excel proyect inspired by [Tsoding-minicel](https://www.youtube.com/watch?v=HCAgvKQDJng)
>- Attemp to replicate the proyect in java

## Features
- [x] resolves expresions with 2 or more elements
- [x] resolves expresions with priority
- [x] resolves expresions with multiples operations

## Example
>- executes the normal math operations
```console
.<A>.<B>.<C>.<D>
.<12>.<10>.<23>.<=A2+C4+(A1+C2)>
.<1>.<2>.<18>.<=A2*B2*(A1*B4)*A2>
.<12>.<10>.<15>.<=A3+(B3+C1)>
.<15>.<3>.<6>.<=A1/(C4/B2)>
.<>.<>.<>.<=A3-(C4-A2)>
```
### output 
>- expected output when using "()" to set priority
```console
Resultado sum :
A2+C4==7
A1+C2==30
A2+C4+(A1+C2)==37

Resultado mult :
A2*B2==2
A1*B4==36
A2==1
A2*B2*(A1*B4)*A2==72
Resultado sum :
A3==12
B3+C1==33
A3+(B3+C1)==45

Resultado div :
A1==12.0
C4/B2==3.0
A1/(C4/B2)==4.0
Resultado rest :
A3==12
C4-A2==5
A3-(C4-A2)==7
```

------

## TODO's
- [ ] integrate the functionality to use the result of the cell to compute another cell
>- EJM:
>>- `<C3>-<B1>-<=C3*B1>`
>>- `C3`: contains the operations: `<A1*B1>`
>>- in order to resolve the operation `C3*B1` wee need to have the result of `C3` first
