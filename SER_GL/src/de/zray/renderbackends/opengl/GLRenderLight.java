/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.renderbackends.opengl;

import de.zray.se.graphics.LightSource;
import de.zray.se.logger.SELogger;
import de.zray.se.world.World;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_AMBIENT;
import static org.lwjgl.opengl.GL11.GL_DIFFUSE;
import static org.lwjgl.opengl.GL11.GL_LIGHT0;
import static org.lwjgl.opengl.GL11.GL_LIGHT7;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_LIGHT_MODEL_LOCAL_VIEWER;
import static org.lwjgl.opengl.GL11.GL_NORMALIZE;
import static org.lwjgl.opengl.GL11.GL_POSITION;
import static org.lwjgl.opengl.GL11.GL_SPECULAR;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLightfv;

/**
 *
 * @author vortex
 */
public class GLRenderLight {
    public void renderLightSources(World world){
        if(world.getAllLights() != null || !world.getAllLights().isEmpty()){
            glEnable(GL_LIGHTING);
            glEnable(GL_NORMALIZE);
            GL11.glLightModelf(GL_LIGHT_MODEL_LOCAL_VIEWER, GL_TRUE);
            for(int i = 0; i < 8; i++){
                renderLight(world.getAllLights().get(i), i);
            }
            
        } else {
            glDisable(GL_LIGHTING);
        }
    }
    
    private void renderLight(LightSource light, int num){
        int lightNum = GL_LIGHT0+num;
        if(lightNum > GL_LIGHT7 || lightNum < GL_LIGHT0){
            SELogger.get().dispatchMsg("GLRenderer", SELogger.SELogType.WARNING, new String[]{"Lightnuber dose not exist!"}, false);
        }
        
        glEnable(lightNum);
        float position[] = new float[4];
        position[0] = (float) light.getOrientation().getPosition()[0];
        position[1] = (float) light.getOrientation().getPosition()[1];
        position[2] = (float) light.getOrientation().getPosition()[2];
        position[3] = 1;
        
        glLightfv(lightNum, GL_POSITION, position);
        applyLightColors(light, lightNum);
        
    }
    
    private void applyLightColors(LightSource light, int lightNum){
        glLightfv(lightNum, GL_DIFFUSE, light.getColor(LightSource.DIFFUSE));
        glLightfv(lightNum, GL_AMBIENT, light.getColor(LightSource.AMBIENT));
        glLightfv(lightNum, GL_SPECULAR, light.getColor(LightSource.SPECULAR));
    }
    
    private void applyLightType(LightSource light, int lightNum){
        switch(light.getLightType()){
            case POINT :
            case VOLUME :
                break;
            case SPOT :
                break;
            case SUN :
                break;
        }
    }
}
