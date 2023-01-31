import os
import shutil

class Ejercicio1:

    def menu(self):
        num = (int)(input("""Hola, ¿qué quieres hacer hoy?
        1-Crea un directorio
        2-Listar un directorio
        3-Copiar un archivo
        4-Mover un archivo
        5-Eliminar un archivo/directorio
        6-Salir del programa
        
        """))

        if num == 1:
            self.metodo1()
        elif num == 2:
            self.metodo2()
        elif num == 3:
            self.metodo3()
        elif num == 4:
            self.metodo4()
        elif num == 5:
            self.metodo5()
        elif num == 6:
            self.metodo6()
        else:
            self.menu()


    def metodo1(self):
        #Crea un directorio
        ruta = input("Ruta del directorio")
        nombre = input("Nombre del directorio")
        if not(os.path.exists(ruta)):
            print("No es una ruta")
        else:
            #Crea carpeta
            os.mkdir(os.path.join(ruta, nombre))
        self.menu()

    def metodo2(self):
        #Listar un directorio(directorios-archivos)
        ruta = input("Ruta del directorio a listar")
        #print(os.listdir(ruta))
        archivos = [name for name in os.listdir(ruta)
                    if os.path.isfile(os.path.join(ruta, name))]
        directorios = [name for name in os.listdir(ruta)
                       if os.path.isdir(os.path.join(ruta, name))]
        for arc in archivos:
            print(arc)
        for dir in directorios:
            print("*" + dir + "*")
        self.menu()




    def metodo3(self):
        #Copiar un archivo
        ruta1 = input("Ruta del original")
        ruta2 = input("Ruta de destino")
        shutil.copy(ruta1, ruta2)
        self.menu()

    def metodo4(self):
        #Mover un archivo
        ruta1 = input("Ruta del original")
        ruta2 = input("Ruta de destino")
        shutil.move(ruta1, ruta2)
        self.menu()

    def metodo5(self):
        #Eliminar un archivo / directorio
        ruta = input("Ruta a borrar")
        if os.path.isdir(ruta):
            try:
                #Borra carpeta
                os.rmdir(ruta)
            except:
                print("Esta lleno")
                #Borra carpeta ytodo lo que tenga dentro
                shutil.rmtree(ruta)
        else:
            #Borra fichero
            os.remove(ruta)
        self.menu()

    def metodo6(self):
        print("Adio")

e = Ejercicio1()
e.menu()
