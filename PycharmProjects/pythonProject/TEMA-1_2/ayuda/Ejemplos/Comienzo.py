from statistics import mean

def sumatorio():
    print(sum(list))

def media():
    print(mean(list))

def maximo():
    print(max(list))

def minimo():
    print(min(list))

list=[]
for cont in range(3):
    variab = (int)(input("Introduce un numero"))
    while variab%2==0:
        variab = (int)(input("ERROR: Introduce un numero IMPAR"))
    list.append((variab))

print(list)



print("¿Que desea hacer con la lista?")
print("1. Sumatorio")
print("2. Media")
print("3. Máximo")
print("4. Minimo")
print()
respuesta = (int)(input("0. Salir   "))
while respuesta!=0:
    if respuesta==1:
        sumatorio()
    elif respuesta==2:
        media()
    elif respuesta==3:
        maximo()
    elif respuesta==4:
        minimo()
    else:
        break
    print()
    print()
    print("¿Que desea hacer con la lista?")
    print("1. Sumatorio")
    print("2. Media")
    print("3. Máximo")
    print("4. Minimo")
    print()
    respuesta = (int)(input("0. Salir   "))


