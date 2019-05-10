/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.renderbackends.opengl.debug;

import de.zray.se.EngineSettings;
import de.zray.se.graphics.semesh.Face;
import de.zray.se.graphics.semesh.Mesh;
import de.zray.se.graphics.semesh.MeshData;
import de.zray.se.graphics.semesh.Normal;
import de.zray.se.graphics.semesh.Vertex;
import de.zray.se.storages.AssetLibrary;
import de.zray.se.world.Actor;
import de.zray.se.world.DistancePatch;
import de.zray.se.world.Entity;
import de.zray.se.world.World;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author vortex
 */
public class GLDebugRenderer {
    private GLDebugGrid gridRenderer = new GLDebugGrid();
    private GLDebugDistnacePatch dpRenderer = new GLDebugDistnacePatch();
    private GLDebugBBox bBoxRender = new GLDebugBBox();
    private GLDebugNormal nRenderer = new GLDebugNormal();
    
    public void render(World world){
        glDisable(GL_LIGHTING);
        glDisable(GL_TEXTURE_2D);
        glCullFace(GL_FALSE);
        if(EngineSettings.get().debug.renderOnTop){
            glDisable(GL_DEPTH_TEST);
        }        
        glLineWidth(1);
        
        if(EngineSettings.get().debug.showGrid){
            renderGrid();
        }
        if(EngineSettings.get().debug.showDistancePatches){
            renderDistancePatches(world);
        }
        for(Actor act : world.getVisibleActors()){
            if(act.getBoundingBox() != null){
                bBoxRender.renderBoundingBox(act.getBoundingBox());
                //nRenderer.renderNormals(act);
            }
        }
    }
    
    private void renderGrid(){
        gridRenderer.renderGrid();
        
    }
    
    private void renderDistancePatches(World world){
        for(DistancePatch dp : world.getDistancePatched()){
            dpRenderer.renderDistnacePatch(dp);
        }
    }
}
