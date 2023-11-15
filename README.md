# Mini excel proyect

>- Mini excel proyect base on [tsoding-miniExcel](https://www.youtube.com/watch?v=HCAgvKQDJng)
>- Attemp to replicate the proyect in java

### Example
>- with numbers
>>- executes the normal math operations
```console
<A>-<B>-<C>
<12>-<10>-<=A1+B1>
<1>-<2>-<=A1-B1>
<12>-<10>-<=A1*B1>
<15>-<3>-<=A2/B2>
```

>- with text
```console
<A>-<B>-<C>
<alf>-<sebas>-<=A2+B2>
<alf>-<2>-<=A1*B1>
<alf>-<3>-<=A2-B2>
```
>>- the `*`: means repeat *A* with *B* ammount
>>- the `-`: means remove *B* ammount of *A* caracters
>>- the `+`: means add or concat *B* to *A*

## Features 
- [x] numeric operation in lineal order
- [x] numeric operation between columns not matter the row
>>- `<12>-<10>-<=A4*B1>`


## TODO's
- [ ] integrate the functionality to use the result of the operation to compute another operation
>>- `<C3>-<B1>-<=C3*B1>`
>>- `C3`: contains the operations so: `c3 = <A1*B1>`
>>- this means that if you use `c3` to compute the result of C3 with B1 this will generate an error because
>>>- C3 contains the operation not the result of the operation
