from builtins import input

import mysql.connector

class EXAMEN:

    # *********************************************************************************************************************
    # METODOS EJERCICIO1
    def listadoAlumnos(self):
        conDB = mysql.connector.connect(host="172.20.132.130", user="ex2", password="adat",database="examen2")
        cur = conDB.cursor()

        sqlAlumnos="Select a.APENOM, asi.ABREVIATURA, n.NOTA from alumnos a, asignaturas asi, notas n " \
                   "Where a.DNI=n.DNI and asi.COD=n.COD order by APENOM desc   "
        cur.execute(sqlAlumnos)
        todosAlumnos = cur.fetchall()
        nombre=""
        listNoRepetirAlumnos=[]
        for i in todosAlumnos:
            nombre=i[0]
            asig=i[1]
            nota=i[2]
            if not nombre in listNoRepetirAlumnos:
                listNoRepetirAlumnos.append(nombre)
                print("\n",nombre,"""
--------------------------------------------------------------""")
            print(asig,"\t\t\t",nota)
    # *********************************************************************************************************************
    # METODOS EJERCICIO2
    def modificarNombreAlumno(self):
        conDB = mysql.connector.connect(host="172.20.132.130", user="ex2", password="adat", database="examen2")
        cur = conDB.cursor()

        dni = 0
        esta=self.comprobarDni("dni")

        while(esta==False):
            print("Introduzca el dni del alumno existente que va a modificar")
            dni = input("")
            esta = self.comprobarDni(dni)
        print(self.sacarNombre(dni)[0][0])
        print("Escribe el nuevo nombre del alumno:")
        nom=input("")
        if(nom != ""):
            sqlNombre="UPDATE alumnos SET APENOM = %s WHERE (`DNI` = %s);"
            cur.execute(sqlNombre,(nom,dni))
        conDB.commit()


    def comprobarDni(self, dni):
        conDB = mysql.connector.connect(host="172.20.132.130", user="ex2", password="adat", database="examen2")
        cur = conDB.cursor()

        sqlDNI = "Select DNI from alumnos "
        esta=False
        cur.execute(sqlDNI)
        todoDni = cur.fetchall()
        for i in todoDni:
            if i[0] == dni:
                esta=True

            # print(i)

        return esta
    def sacarNombre(self, dni):
        conDB = mysql.connector.connect(host="172.20.132.130", user="ex2", password="adat", database="examen2")
        cur = conDB.cursor()
        sqlDNI = "Select APENOM from alumnos where DNI = %s"
        cur.execute(sqlDNI,(dni,))
        nombre = cur.fetchall()

        return nombre



    # *********************************************************************************************************************
    # METODOS EJERCICIO3
    def modificarAniadirCalificacion(self):
        conDB = mysql.connector.connect(host="172.20.132.130", user="ex2", password="adat", database="examen2")
        cur = conDB.cursor()

        dni = 0
        esta = self.comprobarDni("dni")

        while (esta == False):
            print("Introduzca el dni del alumno existente que va a modificar")
            dni = input("")
            esta = self.comprobarDni(dni)

        print(self.sacarNombre(dni)[0][0])
        print("Listado de asignaturas disponibles:")
        listAsig=self.listarAsignaturas()
        cont=1
        dictAsig=dict()
        for i in listAsig:
            dictAsig[cont]=i
            print(cont,".-",i[0])
            cont+=1
        print("Escribe el codigo de la asignatura a evaluar")
        codAsig=(int)(input(""))
        nomAsig=dictAsig[codAsig][0]
        print("Escribe la nota del Alumno")
        notaNueva=input("")

        listAsigAlumn=self.listarAsignaturasPorAlumno(dni)
        mod=False
        for e in listAsigAlumn:
            if(e[0] == nomAsig):
                mod=True
        if(mod==True):
            COD=self.sacarCOD(nomAsig)
            sqlModificar="UPDATE notas SET NOTA = %s WHERE (DNI = %s) and (COD = %s);"
            cur.execute(sqlModificar,(notaNueva,dni,COD[0][0]))
            conDB.commit()
        else:
            COD=self.sacarCOD(nomAsig)
            sqlModificar="UPDATE notas SET NOTA = %s WHERE (DNI = %s) and (COD = %s);"
            cur.execute(sqlModificar,(notaNueva,dni,COD[0][0]))
            conDB.commit()






    def listarAsignaturas(self):
        conDB = mysql.connector.connect(host="172.20.132.130", user="ex2", password="adat", database="examen2")
        cur = conDB.cursor()

        sqlDNI = "Select NOMBRE from asignaturas "
        cur.execute(sqlDNI)
        todoAsignaturas = cur.fetchall()
        # for i in todoAsignaturas:
        #     print(i)
        return todoAsignaturas
    def listarAsignaturasPorAlumno(self,dni):
        conDB = mysql.connector.connect(host="172.20.132.130", user="ex2", password="adat", database="examen2")
        cur = conDB.cursor()

        sqlDNI = "Select asi.NOMBRE from notas n, alumnos a, asignaturas asi where n.DNI=a.DNI and n.COD=asi.COD" \
                 " AND n.DNI=%s  "
        cur.execute(sqlDNI,(dni,))
        todoAsignaturas = cur.fetchall()
        # for i in todoAsignaturas:
        #     print(i)
        return todoAsignaturas
    def sacarCOD(self, asig):
        conDB = mysql.connector.connect(host="172.20.132.130", user="ex2", password="adat", database="examen2")
        cur = conDB.cursor()

        sqlDNI = "Select COD from asignaturas where %s = NOMBRE"
        cur.execute(sqlDNI, (asig,))
        COD = cur.fetchall()
        return COD
    # *********************************************************************************************************************
    # MENU
    def menu(self):
        resp=(int)(input("""\n\n\nElije el ejercicio
        1.-Ejercicio1
        2.-Ejercicio2
        3.-Ejercicio3
        4.-Ejercicio4"""))
        if(resp==1):
            self.listadoAlumnos()
            self.menu()
        elif(resp==2):
            self.modificarNombreAlumno()
            self.menu()
        elif(resp==3):
            self.modificarAniadirCalificacion()
            self.menu()


ex=EXAMEN()
ex.menu()