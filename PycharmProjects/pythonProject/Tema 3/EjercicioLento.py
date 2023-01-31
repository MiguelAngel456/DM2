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
            equipo = dict()
            deporte = dict()
            olimpiada = dict()
            deportista = list()
            evento = dict()
            print("Ya queda poco :)")
            with open(elcsv) as csvfile:
                reader = csv.DictReader(csvfile)
                for row in reader:
                    if not row["Team"] in equipo.values():
                        sql = "INSERT INTO Equipo VALUES(%s,%s,%s)"
                        cur = conDB.cursor()
                        cur.execute(sql, (None, row["Team"], row["NOC"]))
                        conDB.commit()
                        equipo[cur.lastrowid] = row["Team"]

                    if not row["Sport"] in deporte.values():
                        sql = "INSERT INTO Deporte VALUES(%s,%s)"
                        cur = conDB.cursor()
                        cur.execute(sql, (None, row["Sport"]))
                        conDB.commit()
                        deporte[cur.lastrowid] = row["Sport"]

                    if not row["Games"] in olimpiada.values():
                        sql = "INSERT INTO Olimpiada VALUES(%s,%s,%s,%s,%s)"
                        cur = conDB.cursor()
                        cur.execute(sql, (None, row["Games"], row["Year"], row["Season"], row["City"]))
                        conDB.commit()
                        olimpiada[cur.lastrowid] = row["Games"]

                    if not row["ID"] in deportista:
                        sql = "INSERT INTO Deportista VALUES(%s,%s,%s,%s,%s)"
                        cur = conDB.cursor()
                        cur.execute(sql, (row["ID"], row["Name"], row["Sex"], row["Weight"], row["Height"]))
                        conDB.commit()
                        deportista.append(row["ID"])

                    if not row["Event"] in evento.values():
                        idOlimpiada = get_key(row["Games"], olimpiada)
                        idDeporte = get_key(row["Sport"], deporte)
                        sql = "INSERT INTO Evento VALUES(%s,%s,%s,%s)"
                        cur = conDB.cursor()
                        cur.execute(sql, (None, row["Event"], idOlimpiada, idDeporte))
                        conDB.commit()
                        datosEvento = row["Games"] + " " + row["Event"]
                        evento[cur.lastrowid] = datosEvento

                    idEvento = get_key(datosEvento, evento)
                    idEquipo = get_key(row["Team"],equipo)
                    sql = "INSERT INTO Participacion VALUES(%s,%s,%s,%s,%s)"
                    cur = conDB.cursor()
                    cur.execute(sql, (row["ID"], idEvento, idEquipo, row["Age"], row["Medal"]))
                    conDB.commit()


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
