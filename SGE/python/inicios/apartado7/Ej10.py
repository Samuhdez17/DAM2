def aDecimal(n):
    n = list(n)
    n.reverse()

    decimal = 0

    for i in range(len(n)):
        decimal += int(n[i]) * 2 ** i

    return decimal

def aBinario(n):
    binario = []

    while n > 0:
        binario.append(str(n % 2))
        n //= 2
    binario.reverse()

    return ''.join(binario)

print(aDecimal('10110'))
print(aBinario(22))
print(aDecimal(aBinario(22)))
print(aBinario(aDecimal('10110')))