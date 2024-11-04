# Sistema Aplicación Bancaria
Se necesita modelar un sistema que simule una aplicación bancaria. Este sistema debe
poder permitir realizar transferencias entre cuentas. Se debe poder ver un historial de las
transacciones realizadas, en donde se muestre el número de cuenta a la que se realizó la
transacción (ya sea cargo o abono), la cantidad y la fecha. Cada cliente tiene una cuenta
corriente, y también puede tener una cuenta de ahorro (aunque no es necesario). Tanto las
cuentas corrientes como las de ahorro se le realizan actualizaciones mensuales a su saldo,
pero de manera diferente. A las cuentas corrientes se le resta un costo de mantención fijo,
mientras que a las cuentas de ahorros se le suma el total de los últimos 5 depósitos más
un porcentaje de rentabilidad.

## Clases

- Cliente
- CuentaBancaria (super clase abstracta)
- CuentaCorriente (subclase)
- CuentaAhorro (subclase)
- Transaccion
- Banco

