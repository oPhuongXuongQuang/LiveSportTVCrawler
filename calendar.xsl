<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : calendar.xsl
    Created on : April 18, 2016, 8:18 PM
    Author     : quangphuong
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml"/>
    <xsl:param name="pathPrefix" select="'http://localhost:8080/LiveSportTVCrawler/lib/img/'" />
    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:attribute-set name="padding-header">
      <xsl:attribute name="padding-top">4px</xsl:attribute>
      <xsl:attribute name="padding-right">4px</xsl:attribute>
      <xsl:attribute name="padding-bottom">4px</xsl:attribute>
      <xsl:attribute name="padding-left">4px</xsl:attribute>
    </xsl:attribute-set>
    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="calendar">
                    <fo:region-body margin-top="0.5in"/>
                    <fo:region-before extent="1in"/>
                    <fo:region-after margin-top="1in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="calendar">
                <fo:static-content>
                    <fo:block text-align="center">
                        <xsl:choose>
                            <xsl:when test="calendar/@id=1">
                                <fo:external-graphic src="{concat('url('$pathPrefix,epl.png')')}">
                                </fo:external-graphic>
                            </xsl:when>
                            <xsl:when test="calendar/@id=10">
                                <fo:external-graphic src="{concat('url('$pathPrefix,laliga.jpg')')}">
                                </fo:external-graphic>
                            </xsl:when>
                            <xsl:when test="calendar/@id=11">
                                <fo:external-graphic src="{concat('url('$pathPrefix,seriea.png')')}">
                                </fo:external-graphic>
                            </xsl:when>
                            <xsl:when test="calendar/@id=22">
                                <fo:external-graphic src="{concat('url('$pathPrefix,eredivisie.png')')}">
                                </fo:external-graphic>
                            </xsl:when>
                            <xsl:when test="calendar/@id=36">
                                <fo:external-graphic src="{concat('url('$pathPrefix,bundesliga.jpeg')')}">
                                </fo:external-graphic>
                            </xsl:when>
                            <xsl:when test="calendar/@id=37">
                                <fo:external-graphic src="{concat('url('$pathPrefix,ligue1.png')')}">
                                </fo:external-graphic>
                            </xsl:when>
                            <xsl:when test="calendar/@id=93">
                                <fo:external-graphic src="{concat('url('$pathPrefix,brazil.png')')}">
                                </fo:external-graphic>
                            </xsl:when>
                        </xsl:choose>
                    </fo:block>
                </fo:static-content>
                <fo:flow flow-name="xsl-region-body">
                    <xsl:for-each select="calendar/round">
                        <fo:block text-align="center" background-color="orange" font-weight="bold" font-size="14pt"
                            xsl:use-attribute-sets="padding-header">
                            Round <xsl:value-of select="@id"></xsl:value-of>
                        </fo:block>
                        <fo:block>
                            <fo:table>
                                <fo:table-column />
                                <fo:table-column column-width="10cm"/>
                                <fo:table-column />
                                <fo:table-column />
                                <fo:table-column />
                                <fo:table-body>
                                    <fo:table-row background-color="gray" font-weight="bold" font-size="12pt" color="white">
                                        <fo:table-cell>
                                            <fo:block text-align="center">Date</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block text-align="center">Macth</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block text-align="center">Home</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block text-align="center">Score</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block text-align="center">Away</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <xsl:for-each select="match">
                                        <fo:table-row>
                                            <fo:table-cell>
                                                <fo:block text-align="center">
                                                    <xsl:value-of select="date"></xsl:value-of>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block text-align="center">
                                                    <xsl:value-of select="teams"></xsl:value-of>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block text-align="center">
                                                    <fo:external-graphic src="{concat('url(',logoTeam1 , ')')}">
                                                    </fo:external-graphic>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block text-align="center">
                                                    <xsl:value-of select="score"></xsl:value-of>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block text-align="center">
                                                    <fo:external-graphic src="{concat('url(',logoTeam2 , ')')}">
                                                    </fo:external-graphic>
                                                </fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </xsl:for-each>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>
                    </xsl:for-each>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

</xsl:stylesheet>
