<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">

<html>
<head>
    <title>Inventario</title>

    <style>
        body{
            font-family: Arial;
            background:#f4f4f4;
            padding:20px;
        }

        table{
            border-collapse: collapse;
            width: 100%;
            background:white;
        }

        th, td{
            border:1px solid #ccc;
            padding:10px;
        }

        th{
            background:#333;
            color:white;
        }
    </style>
</head>

<body>

<h1>Inventario del Taller</h1>

<table>

<tr>
    <th>Material</th>
    <th>Armario</th>
    <th>Balda</th>
    <th>Cantidad</th>
</tr>

<xsl:for-each select="inventario/material">

<tr>
    <td><xsl:value-of select="nombre"/></td>
    <td><xsl:value-of select="armario"/></td>
    <td><xsl:value-of select="balda"/></td>
    <td><xsl:value-of select="cantidad"/></td>
</tr>

</xsl:for-each>

</table>

</body>
</html>

</xsl:template>

</xsl:stylesheet>