def main():
    a = 0
    b = 0
    c = 0.0
    w = 0.0
    x = ''
    i = 0
    y = 0.0
    j = 0
    k = 0.0
    print("Hello World")
    print("Fim do programa")
    c = -1.1
    c = 1+2
    k = 200*2+120*3
    w = 200*3/2
    a = int(input())
    b = int(input())
    print(a)
    i = 0
    j = 0
    while i<9 or j<9:
        i = i+1
        print("estou no while")
    if a>5:
        print("maior que 5")
    else:
        print("menor que 5")
    while True:
        j = j+1
        print("estou no doWhile")
        if not ((not(j>=9))):
            break
    while i<10 and j<10:
        j = j+1
        i = i+1
        print("teste and")
    print("oi agora vamos testar outro")
    if b>=0:
        print("b positivo")
    else:
        print("b negativo")

if __name__ == '__main__':
    main()

