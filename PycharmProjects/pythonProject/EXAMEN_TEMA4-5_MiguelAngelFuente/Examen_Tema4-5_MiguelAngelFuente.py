from sqlalchemy import create_engine, desc
from sqlalchemy.orm import sessionmaker

from Mapeo import notas, alumnos, asignaturas

class Examen:
    def menu(self):
        num = (int)(input("""Hola, ¿qué quieres hacer hoy?
        1-Listado de asignaturas y notas por Alumnos
        2-Cambiar nombre en caso de que exista
        3-Añadir deportista/participación
        0-Salir del programa
    
        """))

        if num == 1:
            self.listadoAlumnos()
        elif num == 2:
            self.cambiarNombre()
        elif num == 3:
            self.añadirModificarNota()
        else:
            print("Adio")
    # -------------EJERCICIO1-------------
    def listadoAlumnos(self):
        sesion=bbddSession()
        listaAl = sesion.query(alumnos).order_by(desc(alumnos.APENOM))
        for e in listaAl:
            print(e.APENOM)
            print("-----------------------------------------------------------------------------------------------------")
            for n in e.notas:
                print(n.asignatura.ABREVIATURA,"         ",n.NOTA)
            print("\n")
        sesion.close()
    # -------------EJERCICIO2-------------
    def cambiarNombre(self):
        sesion=bbddSession()
        dni=input("Escribe el DNI del alumno que deseas modificar")
        alumn= sesion.query(alumnos).filter(alumnos.DNI==dni).first()
        if alumn is None:
            print("Ese DNI no existe adios")
        else:
            print(alumn.APENOM)
            nuevoNom=input("Escribe el nuevo nombre para el alumno")
            if nuevoNom.__sizeof__()>0:
                alumn.APENOM=nuevoNom
                print("Alumno modificado correctamente")
                sesion.commit()
            else:
                print("No has escrito ningun nombre")
            print("\nFin del programa")
        sesion.close()
    # -------------EJERCICIO3-------------
    def añadirModificarNota(self):
        sesion=bbddSession()
        dni = input("Escribe el DNI del alumno que deseas modificar")
        alumn= sesion.query(alumnos).filter(alumnos.DNI==dni).first()
        if alumn is None:
            print("Ese DNI no existe adios")
        else:
            print(alumn.DNI)
            print("Listado de asignaturas disponibles:")
            listAsignatura= sesion.query(asignaturas).all()
            cont=0
            for a in listAsignatura:
                cont+=1
                print(cont,"-.",a.NOMBRE)
            print("Escribe el codigo de la asignatura a evaluar")
            opc=(int)(input())
            Elegida=listAsignatura.__getitem__(opc-1)
            codElegida=Elegida.COD
            esta=False
            for n in alumn.notas:
                if n.COD==codElegida:
                    esta=True
            if esta==True:
                print("Ya tienes una nota en esa asignatura , introduce la nueva nota")
                notaNueva = (int)(input())
                for n in alumn.notas:
                    if n.COD == codElegida:
                        n.NOTA=notaNueva
                print("La nota se a añadido")
            else:
                print("No tienes una nota en esa asignatura , introduce la nueva nota")
                notaNueva = (int)(input())
                nota=notas()
                nota.DNI=alumn.DNI
                nota.COD=codElegida
                nota.NOTA=notaNueva
                sesion.add(nota)
                print("La nota se a añadido")

        sesion.commit()
        sesion.close()





def bbddSession():
    engine = create_engine('mysql+pymysql://ex2:adat@172.20.132.130/examen2')
    Session = sessionmaker(bind=engine)
    sesion = Session()
    return sesion
ex=Examen()
ex.menu()