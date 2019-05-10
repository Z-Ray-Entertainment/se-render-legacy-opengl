/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.renderbackends.opengl.debug;

import de.zray.se.graphics.semesh.Mesh;
import de.zray.se.graphics.semesh.MeshData;
import de.zray.se.graphics.semesh.Normal;
import de.zray.se.graphics.semesh.Orientation;
import de.zray.se.graphics.semesh.Vertex;
import de.zray.se.storages.AssetLibrary;
import de.zray.se.world.Actor;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author vortex
 */
public class GLDebugNormal {
    public void renderNormals(Actor act){
        for(Mesh mesh : act.getRendableSEMeshes()){
            MeshData mData = AssetLibrary.get().getMesh(mesh.getSEMeshData());
            if(mData.getVertecies().size() == mData.getNormals().size()){
                long verts = mData.getVertecies().size();
                glBegin(GL_LINES);
                for(int i = 0; i < verts; i++){
                    Vertex v = mData.getVertecies().get(i);
                    Normal n = mData.getNormals().get(i);
                    Vector3d pos = act.getOrientation().getPositionVec();

                    switch(i%3){
                        case 0 :
                            glColor3f(1, 0, 0);
                            break;
                        case 1 :
                            glColor3f(0, 1, 0);
                            break;
                        case 2 :
                            glColor3f(0, 0, 1);
                            break;
                    }
                    glVertex3d(pos.x+v.vX, pos.y+v.vY, pos.z+v.vZ);
                    glVertex3d(pos.x+v.vX+n.nX, pos.y+v.vY+n.nY, pos.z+v.vZ+n.nZ);
                }
                glEnd();
            }
        }
    }
}
