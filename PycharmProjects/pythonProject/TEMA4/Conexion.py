from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from mapeo import  Alumno, Nota

engine = create_engine("mysql+pymysql://admin:password@localhost/olimpiadas")#, echo=True
Session= sessionmaker(bind=engine)
session=Session()
session.execute("pragma foreign_keys=on")
# a=Alumno(nombre="Mikel")
a=session.query(Alumno).filter(Alumno.id_Alumno==1).one()
print("Notas de :",a.nombre)

for r in a.notas:
    print(r.module,'        ',r.nota)

# a.notas = [Nota(id_alumno=1, modulo="ADAT",nota=8),Nota(id_alumno=1,modulo="SIGE",nota=1),Nota(id_alumno=1,modulo="DEIN",nota=3)]
session.add(a)
session.commit()
session.close()