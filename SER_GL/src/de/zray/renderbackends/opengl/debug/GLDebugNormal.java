/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.renderbackends.opengl.debug;

import de.zray.se.graphics.semesh.Mesh;
import de.zray.se.graphics.semesh.MeshData;
import de.zray.se.graphics.semesh.Normal;
import de.zray.se.graphics.semesh.Vertex;
import de.zray.se.storages.AssetLibrary;
import de.zray.se.world.Actor;
import javax.vecmath.Vector3d;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author vortex
 */
public class GLDebugNormal {
    public void renderNormals(Actor act){
        Vector3d pos = act.getOrientation().getPositionVec();
        Vector3d rot = act.getOrientation().getRotationVec();
        
        for(Mesh mesh : act.getRendableSEMeshes()){
            MeshData mData = AssetLibrary.get().getMesh(mesh.getSEMeshData());
            if(mData.getVertecies().size() == mData.getNormals().size()){
                long verts = mData.getVertecies().size();
                glPushMatrix();
                glTranslated(pos.x, pos.y, pos.z);
                glRotated(rot.x, 1, 0, 0);
                glRotated(rot.y, 0, 1, 0);
                glRotated(rot.z, 0, 0, 1);
                Vector3d scl = mesh.getOffset().getScaleVec();
                glScaled(scl.x, scl.y, scl.z);
                
                glBegin(GL_LINES);
                for(int i = 0; i < verts; i++){
                    Vertex v = mData.getVertecies().get(i);
                    Normal n = mData.getNormals().get(i);
                    glColor3f(.5f, .5f, 1f);
                    glVertex3d(v.vX, v.vY, v.vZ);
                    glVertex3d(v.vX+n.nX, v.vY+n.nY, v.vZ+n.nZ);
                }
                glEnd();
                
                glPopMatrix();
            }
        }
    }
}