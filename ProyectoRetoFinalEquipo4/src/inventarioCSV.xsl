<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="text" encoding="UTF-8"/>

    <xsl:template match="/">

ID,Nombre,Categoria,Estado,Ubicacion,Cantidad
<xsl:for-each select="inventario/material">
<xsl:value-of select="id"/>,<xsl:value-of select="nombre"/>,<xsl:value-of select="categoria"/>,<xsl:value-of select="estado"/>,<xsl:value-of select="ubicacion"/>,<xsl:value-of select="cantidad"/>
<xsl:text>&#10;</xsl:text>
</xsl:for-each>

    </xsl:template>

</xsl:stylesheet>