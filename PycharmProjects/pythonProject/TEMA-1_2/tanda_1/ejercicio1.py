import os
import shutil


class ejercicio1:
    #COSAS DE DIRECTORIOS Y FICHERO

    def menu(self):
        print("MENU")
        opc=(int)(input("""
         1.-Crear un directorio
         2.-Listar un directorio
         3.-Copiar un archivo
         4.-Mover un archivo
         5.-Eliminar un archivo
         6.-salir"""))
        if opc==1:
            self.opc1()
        elif opc==2:
            self.opc2()
        elif opc==3:
            self.opc3()
        elif opc==4:
            self.opc4()
        elif opc==5:
            self.opc5()
        elif opc==6:
            self.opc6()
        else:
            self.menu()

    #CREAR UN DRIRECTORIO
    def opc1(self):
        ruta=input("Inroduce la ruta donde lo quieres")
        nom=input("introduce el nombre")
        os.mkdir(os.path.join(ruta,nom))
        self.menu()

    #LISTAR DIRECTORIOS
    def opc2(self):
        ruta = input("Inroduce la ruta que quieres listar")
        # print(os.listdir(ruta))
        # print(os.list(ruta))
        #diferente
        archivos =[name for name in os.listdir(ruta)
                   if os.path.isfile((os.path.join(ruta,name)))]
        directorio=[name for name in os.listdir(ruta)
                   if os.path.isdir((os.path.join(ruta,name)))]
        for arc in archivos:
            print(arc)
        for direct in directorio:
            print("---"+direct+"----")
        print(os.path.split(ruta)[1])
        self.menu()
    #COPIAR
    def opc3(self):
        ruta=input("Que archivo quieres copiar")
        rutaCopia=input("Donde lo quieres copiar")
        shutil.copyfile(ruta,rutaCopia)
        self.menu()
    #MOVER

    def opc4(self):
        ruta = input("Que archivo quieres Mover")
        rutaDest= input("Donde lo quieres mover")
        shutil.move(ruta,rutaDest)
        self.menu()
    def opc5(self):
        ruta=input("Archivo o directorio que quieres eliminar?")
        if os.path.isdir(ruta):
            try:
                os.rmdir(ruta)
            except:
                print("tiene cosas")
        else:
            os.remove(ruta)
        self.menu()

    def opc6(self):
        print("adios")
        exit()
ej1=ejercicio1()
ej1.menu()