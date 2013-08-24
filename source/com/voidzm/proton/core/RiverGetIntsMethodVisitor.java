/*******
>>> RiverGetIntsMethodVisitor.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
*******/

package com.voidzm.proton.core;

import static org.objectweb.asm.Opcodes.AALOAD;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ASM4;
import static org.objectweb.asm.Opcodes.GETFIELD;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.IALOAD;
import static org.objectweb.asm.Opcodes.IASTORE;
import static org.objectweb.asm.Opcodes.IFEQ;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public class RiverGetIntsMethodVisitor extends MethodVisitor {

	private int iastoreCalls = 0;
	
	public RiverGetIntsMethodVisitor(MethodVisitor visitor) {
		super(ASM4, visitor);
	}

	@Override
	public void visitInsn(int opcode) {
		if(opcode == IASTORE) {
			if(iastoreCalls < 2) {
				iastoreCalls++;
				mv.visitInsn(opcode);
			}
			else {
				iastoreCalls = -999;
				mv.visitInsn(opcode);
				mv.visitFieldInsn(GETSTATIC, "net/minecraft/world/biome/BiomeGenBase", "biomeList", "[Lnet/minecraft/world/biome/BiomeGenBase;");
				mv.visitVarInsn(ALOAD, 5);
				mv.visitVarInsn(ILOAD, 8);
				mv.visitInsn(IALOAD);
				mv.visitInsn(AALOAD);
				mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/world/biome/BiomeGenBase", "getEnableSnow", "()Z");
				Label j1 = new Label();
				mv.visitJumpInsn(IFEQ, j1);
				mv.visitVarInsn(ALOAD, 7);
				mv.visitVarInsn(ILOAD, 8);
				mv.visitFieldInsn(GETSTATIC, "net/minecraft/world/biome/BiomeGenBase", "frozenRiver", "Lnet/minecraft/world/biome/BiomeGenBase;");
				mv.visitFieldInsn(GETFIELD, "net/minecraft/world/biome/BiomeGenBase", "biomeID", "I");
				mv.visitInsn(IASTORE);
				mv.visitLabel(j1);
			}
		}
		else mv.visitInsn(opcode);
	}
	
}
