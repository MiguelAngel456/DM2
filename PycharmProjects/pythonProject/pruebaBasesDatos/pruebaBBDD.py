import mysql.connector
# mysqlDB=mysql.connector.connect(host="localhost",user="admin",password="password",database="aeropuertos")
# sql="INSERT INTO `aeropuertos`.`aviones` (%s,%s,%s,%s,%s,%s);"
# cur=mysqlDB.cursor()
# cur.execute(sql,(17,"Avion de papel",12,4,66,4))
# lista=cur.fetchall()
# for l in lista:
#     print(l[0],"---------",l[1])

conDB=mysql.connector.connect(host="localhost",user="admin",password="password",database="aeropuertos")
cur=conDB.cursor()
with open("alumnos.sql") as f:
    sql=f.read()

resultados=cur.execute(sql,multi=True)
# try:
#     for r in resultados:
#       print(r)
#
# except Exception as e:
#     print("FIN")
sql="create table alumnos3(id int(11))"
cur.execute(sql)
