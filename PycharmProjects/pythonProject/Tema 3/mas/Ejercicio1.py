import csv
from builtins import print

import mysql.connector
import sqlite3

class Ejercici1:
    equipos=dict()
    deportistas=dict()
    deporte=dict()
    Olimpiada=dict()
    Eventos=dict()
    Participacion=dict()


    def menu(self):
        resp=(int)(input("""
        1.-Xrear BBDD MySQL
        2.-Crear BBDD SQLite
        3.-Listado de deportistas en diferentes deportes
        4.-Listado de deportistas participantes
        5.-Modificar medalla deportista
        6.-Añadir deportista/participación
        7.-Eliminar participación
        8.-SALIR
        """))

        if(resp==1):
            self.opc1()
            self.menu()
        elif(resp==2):
            self.opc2()
            self.menu()
        elif(resp == 3):
            self.opc3()
            self.menu()
        elif(resp==4):
            self.opc4()
            self.menu()
        elif(resp==5):
            self.opc5()
            self.menu()
        elif(resp==6):
            self.opc6()
            self.menu()
        elif(resp==7):
            self.opc7()
            self.menu()
        elif(resp==8):
            print("ADIOS")


    def opc1(self):
        #CREAR BBDD
        conDB = mysql.connector.connect(host="localhost", user="admin", password="password")
        cur = conDB.cursor()
        with open("olimpiadas.sql") as f:
            sql = f.read()

        resultados = cur.execute(sql, multi=True)

        try:

            for r in resultados:
                print(r)
            self.sacarInfo()

            # METER EQUIPOS
            sql = "INSERT INTO `Equipo` (`id_equipo`,`nombre`, `iniciales`) VALUES (%s, %s, %s);"
            equip = self.equipos
            print(equip)
            cur.executemany(sql, list(equip.values()))
            conDB.commit()

            # METER ATHLETAS
            sql = "INSERT INTO `Deportista` (`id_deportista`, `nombre`, `sexo`, `peso`, `altura`) VALUES (%s, %s,  %s, %s, %s);"
            cur.executemany(sql, list(self.deportistas.values()))
            conDB.commit()

            # METER DEPORTES
            sql = "INSERT INTO `Deporte` (`id_deporte`, `nombre`) VALUES (%s, %s);"
            cur.executemany(sql, list(self.deporte.values()))
            conDB.commit()

            # METER OLIMPIADA
            sql = "INSERT INTO `Olimpiada` (`id_olimpiada`, `nombre`, `anio`, `temporada`,`ciudad`) VALUES (%s, %s, %s, %s, %s);"
            cur.executemany(sql, list(self.Olimpiada.values()))
            conDB.commit()

            # METER EVENTO
            sql = "INSERT INTO `Evento` (`id_Evento`, `nombre`, `id_olimpiada`, `id_deporte`) VALUES (%s, %s, %s, %s);"
            cur.executemany(sql, list(self.Eventos.values()))
            conDB.commit()

            # METER PARTICIPACION
            sql = "INSERT INTO `Participacion` (`id_deportista`, `id_evento`, `id_equipo`, `edad`, `medalla`) VALUES (%s, %s, %s, %s, %s);"
            cur.executemany(sql, list(self.Participacion.values()))
            conDB.commit()
        except Exception as e:
            print("FIN")

    def sacarInfo(self):


        noRepetirPais = []
        idEquipo=1;

        noRepetirDepor = []
        idDeportista = 1;

        noRepetirDep = []
        idDeporte = 1;

        noRepetirOlimpiada = []
        idOlimpiada = 1;

        noRepetirEvento = []
        idEvento = 1;

        idpart = 1;

        with open("athlete_events-sort.csv") as csvFile:
            reader= csv.DictReader(csvFile)
            # SACAR EL DICCIONARIO DE EQUIPOS:
            for row in reader:
                if not row['Team'] in noRepetirPais:
                    noRepetirPais.append(row['Team'])
                    listPaises = []
                    listPaises.append(idEquipo)
                    listPaises.append(row['Team'])
                    listPaises.append(row['NOC'])
                    # print(listPaises)
                    self.equipos[row['Team']]=listPaises
                    idEquipo+=1;


                #SACAR DEPORTISTAS
                if not row['ID'] in noRepetirDepor:
                    noRepetirDepor.append(row['ID'])
                    listDep=list()

                    listDep.append(row['ID'])
                    listDep.append(row['Name'])
                    # self.nomDeportistas.append(row['Name'])
                    listDep.append(row['Sex'])
                    if row['Weight']=="NA":
                        listDep.append(0)
                    else:
                        listDep.append(row['Weight'])
                    if row['Height']=="NA":
                        listDep.append(0)
                    else:
                        listDep.append(row['Height'])
                    self.deportistas[row['ID']]=listDep
                    idDeportista+=1

                #SACAR DEPORTE
                if not row['Sport'] in noRepetirDep:
                    noRepetirDep.append(row['Sport'])
                    listDepo = list()
                    listDepo.append(idDeporte)
                    listDepo.append(row['Sport'])
                    self.deporte[row['Sport']]=listDepo
                    idDeporte+=1
                #SACAR OLIMPIADAS
                if not row['Games'] in noRepetirOlimpiada:
                    noRepetirOlimpiada.append(row['Games'])
                    listOlimp = list()
                    listOlimp.append(idOlimpiada)
                    listOlimp.append(row['Games'])
                    listOlimp.append(row['Year'])
                    listOlimp.append(row['Season'])
                    listOlimp.append(row['City'])
                    self.Olimpiada[row['Games']]=listOlimp
                    idOlimpiada+=1
                #SACAR EVENTOS
                event=row['Games']+""+row['Event']

                if not event in noRepetirEvento:
                    noRepetirEvento.append(event)
                    listEvent=list()
                    listEvent.append(idEvento)
                    listEvent.append(row['Event'])
                    listEvent.append(self.Olimpiada.get(row['Games'])[0])
                    # print(self.Olimpiada[row['Games']])
                    listEvent.append(self.deporte.get(row['Sport'])[0])
                    self.Eventos[event]=listEvent
                    idEvento+=1
                #SACAR PARTICIPACION

                listPart=[]

                listPart.append(self.deportistas.get(row['ID'])[0])
                # print(self.deportistas.get(row['ID'])[0])
                listPart.append(self.Eventos.get(event)[0])
                # print(self.Eventos.get(event)[0])
                listPart.append(self.equipos.get(row['Team'])[0])
                # print(self.equipos.get(row['Team'])[0])


                if(row['Age']=="NA"):
                    listPart.append("0")
                else:
                    listPart.append(row['Age'])
                if(row['Medal']=="NA"):
                    listPart.append("nada")
                else:
                    listPart.append(row['Medal'])
                #
                self.Participacion[idpart]=listPart
                idpart += 1;



    def opc2(self):
        # CREAR BBDD
        conDB = sqlite3.connect("olimpiadas.db")
        cur = conDB.cursor()
        with open("olimpiadas.db.sql") as c:
            sql = c.read()
        cur.executescript(sql)
        self.sacarInfo()
        # METER EQUIPOS
        sql = "INSERT INTO `Equipo` (`id_equipo`,`nombre`, `iniciales`) VALUES (?, ?, ?);"
        equip = self.equipos
        cur.executemany(sql, list(equip.values()))
        conDB.commit()

        # METER ATHLETAS
        sql = "INSERT INTO `Deportista` (`id_deportista`, `nombre`, `sexo`, `peso`, `altura`) VALUES (?, ?,  ?, ?, ?);"
        cur.executemany(sql, list(self.deportistas.values()))
        conDB.commit()

        # METER DEPORTES
        sql = "INSERT INTO `Deporte` (`id_deporte`, `nombre`) VALUES (?, ?);"
        cur.executemany(sql, list(self.deporte.values()))
        conDB.commit()

        # METER OLIMPIADA
        sql = "INSERT INTO `Olimpiada` (`id_olimpiada`, `nombre`, `anio`, `temporada`,`ciudad`) VALUES (?, ?, ?, ?, ?);"
        cur.executemany(sql, list(self.Olimpiada.values()))
        conDB.commit()

        # METER EVENTO
        sql = "INSERT INTO `Evento` (`id_Evento`, `nombre`, `id_olimpiada`, `id_deporte`) VALUES (?, ?, ?, ?);"
        cur.executemany(sql, list(self.Eventos.values()))
        conDB.commit()



        # METER PARTICIPACION
        sql = "INSERT INTO `Participacion` (`id_deportista`, `id_evento`, `id_equipo`, `edad`, `medalla`) VALUES (?, ?, ?, ?, ?);"

        cur.executemany(sql, list(self.Participacion.values()))
        conDB.commit()

        sql="select nombre from Evento;"
        listaP=cur.execute(sql)
        d=0
        for l in listaP:
            print(l)
            d+=1
        print(d)

    def opc3(self):
        resp = (int)(input("""¿Qué BBDD quieres usar?
       1-MySQL
       2-SQLite
       """))
        sql = """SELECT dep.nombre, sexo, altura, peso, edad, d.nombre, eve.nombre, equ.nombre, oli.nombre, par.medalla
               FROM Deportista dep, Participacion par, Deporte d, Evento eve, Equipo equ, Olimpiada oli
               WHERE dep.id_deportista = par.id_deportista
               AND par.id_equipo = equ.id_equipo
               AND par.id_evento = eve.id_evento
               AND eve.id_deporte = d.id_deporte
               AND eve.id_olimpiada = oli.id_olimpiada
               AND 1<
                   (SELECT count(distinct(eve2.id_deporte))
                   FROM Evento eve2, Participacion par2
                   WHERE eve2.id_evento=par2.id_evento
                   AND par2.id_deportista=dep.id_deportista)
               ;"""
        # MySQL
        if resp == 1:
            conDB = mysql.connector.connect(host="localhost", user="admin", password="password", database="olimpiadas")
            cur = conDB.cursor()
            cur.execute(sql)
            lista = cur.fetchall()
            cont = 0
            for li in lista:
                cont += 1
                print(li)
            print(cont)
            conDB.commit()
            cur.close()
        # SQLite
        elif resp == 2:
            conDB = sqlite3.connect("olimpiadas.db")
            cur = conDB.cursor()
            cur.execute(sql)
            lista = cur.fetchall()
            cont = 0
            for li in lista:
                cont += 1
                print(li)
            print(cont)
            conDB.commit()
            cur.close()

    def opc4(self):
        BBDD = input("MySQL(1) O SQLite(2)")
        if(BBDD==1):
            conDB = mysql.connector.connect(host="localhost", user="admin", password="password", database="olimpiadas")
        else:
            conDB = sqlite3.connect("olimpiadas.db")
        cur = conDB.cursor()
        #Elegir olimpiada
        temporada = input("Winter o Summer (W/S)")
        slqTem= "SELECT nombre FROM Olimpiada WHERE temporada='"+temporada+"' "
        cur.execute(slqTem)
        dictOl= dict()
        cont=1
        listaOl=cur.fetchall()
        for i in listaOl:
            dictOl[cont] = i
            print(cont,".-",i)
            cont+=1
        ol=(int)(input("cual deseas: "))
        nombreOl=dictOl[ol][0]
        # Elegir deporte
        slqTem = "SELECT d.nombre FROM Deporte d, Evento e, Olimpiada o Where d.id_deporte=e.id_deporte AND e.id_Olimpiada=o.id_Olimpiada " \
                 "AND o.nombre= '"+nombreOl+"'"
        cur.execute(slqTem)
        dictOl = dict()
        cont = 1
        listaOl = cur.fetchall()
        for i in listaOl:
            dictOl[cont] = i
            print(cont, ".-", i)
            cont += 1
        ol = (int)(input("cual deseas: "))
        nombredep = dictOl[ol][0]
        # Elegir Evento
        slqTem = "SELECT e.nombre FROM Evento e, Deporte d, Olimpiada o  Where d.id_deporte=e.id_deporte AND e.id_Olimpiada=o.id_Olimpiada " \
                 " AND d.nombre='"+nombredep+"' AND o.nombre= '"+nombreOl+"'"
        cur.execute(slqTem)
        dictOl = dict()
        cont = 1
        listaOl = cur.fetchall()
        for i in listaOl:
            dictOl[cont] = i
            print(cont, ".-", i)
            cont += 1
        ol = (int)(input("cual deseas: "))
        nombreEv = dictOl[ol][0]
        if(BBDD==1):
            sqlDep="SELECT dep.nombre FROM Deporte d, Evento e, Olimpiada ol,Participacion p, Deportista dep WHERE d.id_deporte=e.id_deporte AND e.id_olimpiada=ol.id_olimpiada "\
                   "AND p.id_evento=e.id_evento AND p.id_deportista=dep.id_deportista "\
                   "AND ol.temporada= %s AND ol.nombre= %s AND d.nombre= %s AND e.nombre= %s"
        else:
            sqlDep = "SELECT dep.nombre FROM Deporte d, Evento e, Olimpiada ol,Participacion p, Deportista dep WHERE d.id_deporte=e.id_deporte AND e.id_olimpiada=ol.id_olimpiada " \
                     "AND p.id_evento=e.id_evento AND p.id_deportista=dep.id_deportista " \
                     "AND ol.temporada= ? AND ol.nombre= ? AND d.nombre= ? AND e.nombre= ?"

        cur.execute(sqlDep,(temporada,nombreOl,nombredep,nombreEv))
        listaDep=cur.fetchall()
        for i in listaDep:
            print(i)

    def opc5(self):
        BBDD = (int)(input("MySQL(1) O SQLite(2)"))
        if (BBDD == 1):
            conDB = mysql.connector.connect(host="localhost", user="admin", password="password", database="olimpiadas")
        else:
            conDB = sqlite3.connect("olimpiadas.db")
        cur = conDB.cursor()
        nomIntroducido=input("Introduce el nombre del jugador que quieres buscar")
        # ***********************************************************************************************************
        # Elegir Deportista
        sqlDep="SELECT nombre FROM Deportista Where nombre LIKE '"+nomIntroducido+"%';"
        cur.execute(sqlDep)
        dictDep = dict()
        cont = 1
        listaDep = cur.fetchall()
        for i in listaDep:
            dictDep[cont] = i
            print(cont, ".-", i)
            cont += 1
        dep = (int)(input("cual deseas: "))
        nombreDep = dictDep[dep][0]

        # ***********************************************************************************************************
        # Elegir Evento
        sqlDep = "SELECT e.nombre, e.id_Olimpiada FROM Evento e, Participacion p, Deportista d WHERE " \
                 "e.id_evento = p.id_evento AND p.id_deportista = d.id_deportista AND  " \
                 "d.nombre = '"+nombreDep+"';"
        cur.execute(sqlDep)
        dictEv = dict()
        cont = 1
        listaEv = cur.fetchall()
        for i in listaEv:
            dictEv[cont] = i
            print(cont, ".-", i)
            cont += 1
        ev = (int)(input("cual deseas: "))
        nombreEv = dictEv[ev][0]
        print(nombreEv)
        idOl= dictEv[ev][1]
        # ***********************************************************************************************************
        # Sacar id Eventos y Deportista
        sqlDep="SELECT id_deportista FROM Deportista WHERE nombre ='"+nombreDep+"'"
        cur.execute(sqlDep)
        idDeportista = cur.fetchall()
        if(BBDD==1):
            sqlEv="SELECT id_evento FROM Evento WHERE nombre = %s AND id_Olimpiada = %s"
        else:
            sqlEv = "SELECT id_evento FROM Evento WHERE nombre = ? AND id_Olimpiada = ?"


        cur.execute(sqlEv,(nombreEv,idOl))

        # conDB.commit()
        idEvento = cur.fetchall()
        medalla=input("¿A que medalla le quieres cambiar?")
        idD=(int)(idDeportista[0][0])
        idV=(int)(idEvento[0][0])
        print(str(idD))
        print(str(idV))
        sqlCambio="UPDATE Participacion SET medalla = '"+str(medalla)+"' WHERE (`id_deportista` = '"+str(idD)+"') and (`id_evento` = '"+str(idV)+"');"
        print(sqlCambio)
        cur.execute(sqlCambio)
        conDB.commit()



    def opc6(self):
         BBDD = (int)(input("MySQL(1) O SQLite(2)"))
         if (BBDD == 1):
             conDB = mysql.connector.connect(host="localhost", user="admin", password="password", database="olimpiadas")
         else:
             conDB = sqlite3.connect("olimpiadas.db")
         cur = conDB.cursor()

         nomintroducido=input("Que deportista")
         sqlDep = "SELECT nombre FROM Deportista Where nombre LIKE '" + nomintroducido + "%';"
         cur.execute(sqlDep)
         dictDep = dict()
         cont = 1
         listaDep = cur.fetchall()
         for i in listaDep:
             dictDep[cont] = i
             print(cont, ".-", i)
             cont += 1
         print(cont,".-No esta crearlo, Quiero crearlo")
         dep = (int)(input("cual deseas: "))
         if(dep==cont):
            self.crearDeportista(BBDD)
         #
         temp=input("Que temporadas quieres que sea?")
         if(BBDD==1):
            slqTem = "SELECT nombre FROM Olimpiada WHERE temporada= %s "
         else:
             slqTem = "SELECT nombre FROM Olimpiada WHERE temporada= ? "
         cur.execute(slqTem,(temp,))
         dictOl = dict()
         cont = 1
         listaOl = cur.fetchall()
         for i in listaOl:
             dictOl[cont] = i
             print(cont, ".-", i)
             cont += 1
         ol = (int)(input("cual deseas: "))
         nombreOl = dictOl[ol][0]
         if (BBDD == 1):
             slqTem = "SELECT id_olimpiada FROM Olimpiada WHERE nombre=%s and temporada= %s "
         else:
             slqTem = "SELECT id_olimpiada FROM Olimpiada WHERE nombre=? and temporada= ? "
         cur.execute(slqTem, (nombreOl,temp))
         idOl = cur.fetchall()
    #
         #
         if (BBDD == 1):
             slqTem = "SELECT DISTINCT d.* FROM olimpiadas.Olimpiada o, olimpiadas.Evento e, olimpiadas.Deporte d " \
                      "WHERE o.id_olimpiada=e.id_olimpiada and e.id_deporte=d.id_deporte " \
                      "and o.nombre= %s"
         else:
             slqTem = "SELECT DISTINCT d.* FROM olimpiadas.Olimpiada o, olimpiadas.Evento e, olimpiadas.Deporte d " \
                      "WHERE o.id_olimpiada=e.id_olimpiada and e.id_deporte=d.id_deporte " \
                      "and o.nombre= ?"
         cur.execute(slqTem, (nombreOl,))
         dictDep = dict()
         cont = 1
         listaD = cur.fetchall()
         for i in listaD:
             dictDep[cont] = i
             print(cont, ".-", i)
             cont += 1
         ol = (int)(input("cual deseas: "))
         nombreDEP = dictDep[ol][1]
         if (BBDD == 1):
             slqTem = "SELECT id_deporte FROM olimpiadas.Deporte " \
                      "WHERE  nombre= %s"
         else:
             slqTem = "SELECT id_deporte FROM olimpiadas.Deporte " \
                      "WHERE  nombre= ?"
         print(nombreDEP)
         cur.execute(slqTem,(nombreDEP,))
         idDeporte = cur.fetchone()
    #
    #
         if (BBDD == 1):
             slqTem = "SELECT e.* FROM olimpiadas.Olimpiada o, olimpiadas.Evento e, olimpiadas.Deporte d " \
                      "WHERE o.id_olimpiada=e.id_olimpiada and e.id_deporte=d.id_deporte " \
                      "and o.id_olimpiada=%s and d.id_deporte=%s"
         else:
             slqTem = "SELECT DISTINCT d.* FROM olimpiadas.Olimpiada o, olimpiadas.Evento e, olimpiadas.Deporte d " \
                      "WHERE o.id_olimpiada=e.id_olimpiada and e.id_deporte=d.id_deporte " \
                      "and o.nombre= ?"

         cur.execute(slqTem, (idOl[0][0],idDeporte[0]))
         dictDep = dict()
         cont = 1
         listaD = cur.fetchall()
         for i in listaD:
             dictDep[cont] = i
             print(cont, ".-", i)
             cont += 1
         ol = (int)(input("cual deseas: "))
         nombreEvento = dictDep[ol][0]

         if (BBDD == 1):
             slqTem = "SELECT id_evento FROM olimpiadas.Evento " \
                      "WHERE  nombre= %s and id_olimpiada= %s"
         else:
             slqTem = "SELECT id_evento FROM olimpiadas.Evento " \
                      "WHERE  nombre= ? and id_olimpiada= %s"
         cur.execute(slqTem,(nombreDEP,nombreOl))
         idEvento = cur.fetchone()

         medalla=input("Que medalla?")
         edad=input("Que edad?")
         sql="Select * from Equipo"
         cur.execute(sql)
         dictEquip = dict()
         cont = 1
         listaD = cur.fetchall()
         for i in listaD:
             dictEquip[cont] = i
             print(cont, ".-", i)
             cont += 1
         ol = (int)(input("cual deseas: "))
         idEquipo= dictEquip[0][0]
         print(idEquipo)
         if (BBDD == 1):
             slqTem = "INSERT INTO `olimpiadas`.`Participacion` (`id_deportista`, `id_evento`, `id_equipo`, `edad`, `medalla`) " \
                      "VALUES (%s, %s, %s, %s, %s);"

         else:
             slqTem = "INSERT INTO `olimpiadas`.`Participacion` (`id_deportista`, `id_evento`, `id_equipo`, `edad`, `medalla`) " \
                      "VALUES (%s, %s, %s, %s, %s));"
         id=self.ultimoDeportista()

         cur.execute(slqTem,(id,idEvento,idEquipo,edad,medalla))

    def crearDeportista(self, tipo):
        nom=input("Introduce el nombre")
        sexo=""
        sexo=input("Masculino(M) o Femenino(F)")
        peso=(int)(input("Introduce el peso"))
        altura=(int)(input("Introduce la altura"))


        if(tipo==1):
            conDB = mysql.connector.connect(host="localhost", user="admin", password="password", database="olimpiadas")
            sqlA="INSERT INTO Deportista (id_deportista,nombre, sexo, peso, altura) " \
             "VALUES (%s,%s, %s, %s, %s);"
        else:
            conDB = sqlite3.connect("olimpiadas.db")
            sqlA = "INSERT INTO Deportista (id_deportista,nombre, sexo, peso, altura) " \
                   "VALUES (?,?, ?, ?, ?);"

        cur = conDB.cursor()
        sql = "select max(id_deportista) from Deportista"
        cur.execute(sql)
        resultado = cur.fetchone()
        resultado = resultado[0] + 1

        cur.execute(sqlA,(resultado,nom,sexo,peso,altura))
        conDB.commit()
        return sqlA
    def ultimoDeportista(self,tipo):
        if(tipo==1):
            conDB = mysql.connector.connect(host="localhost", user="admin", password="password", database="olimpiadas")
        else:
            conDB = sqlite3.connect("olimpiadas.db")

        cur = conDB.cursor()
        sql = "select max(id_deportista) from Deportista"
        cur.execute(sql)
        resultado = cur.fetchone()
        resultado = resultado[0] + 1
        return resultado

    def opc7(self):
        BBDD=(int)(input("MySQL(1) o SQLlite(2)"))
        if (BBDD == 1):
            conDB = mysql.connector.connect(host="localhost", user="admin", password="password", database="olimpiadas")
        else:
            conDB = sqlite3.connect("olimpiadas.db")

        cursor = conDB.cursor()
        # Eliminar participacion
        dep = input("Introduce el nombre del deportista: ")

        sqlD = "select id_deportista,nombre from Deportista where nombre LIKE '"+dep+"%'"
        cursor.execute(sqlD)
        resultados = cursor.fetchall()
        participacion = []
        for r in resultados:
            print("ID: ", r[0], " deportista:", r[1])
            participacion.append(r[0])
        deportistaID = int(input("Codigo:"))

        sql = "select Participacion.id_deportista,Olimpiada.nombre,Evento.nombre ,Evento.id_evento from Participacion,Olimpiada,Evento" \
              " where Participacion.id_evento=Evento.id_evento and Evento.id_olimpiada=Olimpiada.id_olimpiada and Participacion.id_deportista=" + str(deportistaID)
        cursor.execute(sql)

        resultados = cursor.fetchall()
        participacion = {}
        cont = 1
        for r in resultados:
            participacion[cont] = r
            print("Codigo: ", cont, " olimpiada: ", r[1], " evento: ", r[2])
            cont += 1
        if (cont > 1):
            participacioncodigo = int(input("Codigo:"))
            self.borrarParticipacion(participacion.get(participacioncodigo)[0], participacion.get(participacioncodigo)[3],BBDD)
        else:
            if cont == 1:
                print("sin participaciones")
            else:
                participacioncodigo = 1
                self.borrarParticipacion(participacion.get(participacioncodigo)[0], participacion.get(participacioncodigo)[3])
                print("borrando deportista")
                self.borrarDeportista(participacion.get(participacioncodigo)[0])


    def borrarParticipacion(self, id_deportista, id_evento,tipo):
        if(tipo==1):
            conexion = mysql.connector.connect(user='admin', password='password', host='localhost')
        else:
            conexion = sqlite3.connect("olimpiadas.db.db")

        cursor = conexion.cursor()
        sql = "delete from olimpiadas.Participacion where id_deportista = " + str(id_deportista) + " and id_evento=" + str(id_evento)
        cursor.execute(sql)
        conexion.commit()


    def borrarDeportista(self, id,tipo):
        if(tipo==1):
            conexion = mysql.connector.connect(user='admin', password='password', host='localhost')
        else:
            conexion = sqlite3.connect("olimpiadas.db")
        cursor = conexion.cursor()
        sql = "delete from Deportista where id_deportista = " + str(id)
        cursor.execute(sql)
        conexion.commit()





ej1=Ejercici1()
ej1.menu()

# SELECT Deportista.*
# FROM Deportista d, Participacion p, Olimpiada ol, Deporte dep, Evento ev
# WHERE d.id_deportista = p.id_deportista AND
# WHERE ol.id_evento = ev.id_evento AND
# WHERE ev.id_deporte;
