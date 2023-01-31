class Criptografo:
    def encriptar(self, texto):
        palabra = ""
        for c in texto:
            palabra+=chr(ord(c)+1)
        return palabra
    def desencriptar(self, texto):
        palabra = ""
        for c in texto:
            palabra += chr(ord(c) - 1)
        return palabra

texto = input("Escribe el texto que quieras encripar")
c = Criptografo()

print(c.encriptar(texto))
print(c.desencriptar(c.encriptar(texto)))