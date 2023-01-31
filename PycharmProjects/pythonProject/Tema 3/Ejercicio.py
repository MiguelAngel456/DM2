import csv
import os
from cgitb import reset

import mysql.connector


def menu():
    num = (int)(input("""Hola, ¿qué quieres hacer hoy?
    1-Crear BBDD MySQL
    2-Crear BBDD SQLite
    3-Listado de deportistas en diferentes deportes
    4-Listado de deportistas participantes
    5-Modificar medalla deportista
    6-Añadir deportista/participación
    7-Eliminar participación
    0-Salir del programa

    """))

    if num == 1:
        metodo1()
    elif num == 2:
        metodo2()
    elif num == 3:
        metodo3()
    elif num == 4:
        metodo4()
    else:
        print("Adio")


def metodo1():
    # elcsv = input("Archivo csv: ")
    elcsv = "mas/athlete_eventsPoco.csv"

    if not (os.path.exists(elcsv)):
        print("El archivo csv no existe")
    else:
        try:
            conDB = mysql.connector.connect(host="localhost", user="admin", password="password")
            cur = conDB.cursor()
            with open("mas/olimpiadas.sql") as f:
                sql = f.read()
            resultados = cur.execute(sql, multi=True)
            print("Un momento")
            try:
                for r in resultados:
                    r
            except Exception as e:
                print("FIN")

            # Lee
            equipo = {}
            deporte = dict()
            olimpiada = dict()
            deportista = dict()
            evento = dict()

            idEquipo = 1
            idDeporte = 1
            idEvento = 1
            idOlimpiada = 1
            cont = 1

            print("Ya queda poco :)")
            with open(elcsv) as csvfile:
                reader = csv.DictReader(csvfile)
                for row in reader:
                    print(cont)
                    cont+=1
                    if not row["Team"] in equipo:
                        lista = []
                        lista.append(idEquipo)
                        lista.append(row["Team"])
                        lista.append(row["NOC"])
                        idEquipo += 1
                        equipo[row["Team"]] = lista
                        print(lista)
                        #lista.clear()
                        print(lista)
                print(equipo)
                cur = conDB.cursor()
                sql = "INSERT INTO Equipo (id_equipo,nombre,iniciales) VALUES (%s, %s, %s)"
                cur.executemany(sql, list(equipo.values()))
                conDB.commit()










                    # if not row["Sport"] in deporte.values():
                    #     print("B")
                    #     lista.append(idDeporte)
                    #     print("B1")
                    #     idDeporte = 2
                    #     print("B2")
                    #     lista.append(row["Sport"])
                    #     print("B3")
                    #     deporte[idDeporte] = lista
                    #     print("B4")
                    #
                    #     lista.clear()
                    #     print("B5")


                    # if not row["Games"] in olimpiada.values():
                    #     print("C")
                    #     lista.append(idOlimpiada)
                    #     idOlimpiada + 1
                    #     lista.append(row["Games"])
                    #     lista.append(row["Year"])
                    #     lista.append(row["Season"])
                    #     lista.append(row["City"])
                    #     olimpiada[idOlimpiada] = lista
                    #     lista.clear()
                    #
                    # if not row["ID"] in deportista.values():
                    #     print("D")
                    #     lista.append(row["ID"])
                    #     lista.append(row["Name"])
                    #     lista.append(row["Sex"])
                    #     lista.append(row["Weight"])
                    #     lista.append(row["Height"])
                    #     deportista[row["ID"]] = lista
                    #     lista.clear()
                    #
                    # if not row["Event"] in evento.values():
                    #     lista.append(idEvento)
                    #     idEvento + 1
                    #     lista.append(row["Event"])
                    #     idOlimpiada = get_key(row["Games"], olimpiada)
                    #     idDeporte = get_key(row["Sport"], deporte)
                    #     lista.append(idOlimpiada)
                    #     lista.append(idDeporte)
                    #     evento[idEvento] = lista
                    #     lista.clear()



            # cur = mysqlDB.cursor()
            # # METER EQUIPOS
            # sql = "INSERT INTO `olimpiadas`.`Equipo` (`id_equipo`,`nombre`, `iniciales`) VALUES (%s, %s, %s);"
            #
            # cur.executemany(sql, list(equip.values()))
            #
            # mysqlDB.commit()


            print(len(equipo))
            print("La carga de la información se ha realizado correctamente")
        except Exception as e:
            print("Ha ocurrido algún error en la carga")
            print(e)
        menu()


def metodo2():
    menu()


def metodo3():
    menu()


def metodo4():
    menu()


def get_key(val, miDict):
    for key, value in miDict.items():
        if val == value:
            return key

    return "key doesn't exist"
menu()
