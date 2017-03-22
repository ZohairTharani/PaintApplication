
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.TexturePaint;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zohai
 */
public abstract class Shape 
{
    int x;
    int y;
    int w;
    int h;

    public abstract void drawShape(Graphics v1, int v2, int v3, int v4, int v5, Color v6, BasicStroke v7, boolean v8, float v9, GradientPaint v10, TexturePaint v11);
}
