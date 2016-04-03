<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <h1>Users Detail</h1>
        <table border="1">
            <thead>
                <th>id</th>
                <th>email</th>
                <th>password</th>
                <th>firstname</th>
                <th>lastname</th>
            </thead>
        </table>
    </xsl:template>
    <xsl:template match="users">
        <xsl:apply-templates select="user">
            <xsl:sort select="email" order="ascending" data-type="text"></xsl:sort>
        </xsl:apply-templates>
    </xsl:template>
    <xsl:template match="user">
        <tr>
            <td>
                <xsl:value-of select="id"/>
            </td>
            <td>
                <xsl:value-of select="email"/>
            </td>
            <td>
                <xsl:value-of select="password"/>
            </td>
            <td>
                <xsl:value-of select="firstname"/>
            </td>
            <td>
                <xsl:value-of select="lastname"/>
            </td>
        </tr>
    </xsl:template>

</xsl:stylesheet>
