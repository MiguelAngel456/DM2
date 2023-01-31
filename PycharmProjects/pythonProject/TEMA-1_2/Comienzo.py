#variab = (int)(input("introduce un numero"))
#print(variab)
#variab2 = (int)(input("introduce un numero"))
#print(variab)
#print(variab+variab2)
def sumatorio(list):
    print(sum(list))
def media(list):
    print(mean(list))
def maximo(list):
    print(print(max(list)))
def minimo(list):
    print(print(min(list)))
from builtins import print
from statistics import mean

#CON FOR
list = []
for cont in range(3):
    variab = (int)(input("introduce un numero"))
    while variab%2 == 0:
        variab = (int)(input("introduce un numero"))

    list.append(variab)
print("Que desea hacer en la lista")
menu =(int) (input(" 1.sumatorio \n 2. Media \n 3.Maximo \n 4. Minimo \n 0 Salir" ))
if menu==1:
    sumatorio(list)
elif menu==2:
    media(list)
elif menu==3:
    maximo(list)
elif menu==4:
    minimo(list)
elif menu==0:
    print("Has salido")

#print(list)
#print(mean(list))





#CON WHILE
#cont = 0
#while cont<3:
#    variab = (int)(input("introduce un numero"))
#    list.append(variab)
#    cont+=1
#print(list)