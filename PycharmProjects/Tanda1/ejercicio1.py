import os
import shutil


class ejercicio1:
    def menu(self):
        print("¿que quieres hacer?")
        menu = (int)(input("""\t1.- crear un directorio
    2.-lista un directorio
    3.-Copiar un archivo
    4.-Mover un archivo
    5.-Eliminar un archivo/directorio
    6.-Salir del programa"""))
        if menu==1:
            self.opc1()
        elif menu==2:
            self.opc2()
        elif menu==3:
            self.opc3()
        elif menu==4:
            self.opc4()
        elif menu==5:
            self.opc5()
        elif menu==6:
            self.opc6()
        return menu
    def opc1(self):
        ruta = input("Ruta donde quiere que este el nuevo directorio")
        nombre = input("Nombre del nuevo directorio que quiere crear")
        os.mkdir(os.path.join(ruta, nombre))
        self.menu()
    def opc2(self):
        ruta = input("Ruta del directorio que quiere listar")
        print("lista de directorios:")
        lista=os.listdir(ruta)
        print(lista)
        print("lista de archivos")
        lista2=os.listxattr()
        print(lista2)

        self.menu()
    def opc3(self):
        ruta = input("Ruta del archivo original")
        rutacop=input("Ruta donde quieres que se copie")
        shutil.copy(ruta,rutacop)
        self.menu()
    def opc4(self):
        ruta = input("Ruta del archivo a mover")
        rutaDestino= input("Ruta donde quieres que se copie")
        shutil.move(ruta,rutaDestino)
        self.menu()
        lista3= [name for name in os.path]
    def opc5(self):
        ruta = input("Ruta del archivo a eliminar")
        if os.path.isfile(ruta):
            os.remove(ruta)
        else:
            os.removedirs(ruta)
    def opc6(self):
        print("Programa terminado")

ej1= ejercicio1()
print(ej1.menu())


