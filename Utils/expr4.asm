extrn lirent:proc, ecrent:proc
extrn ecrbool:proc
extrn ecrch:proc, ligsuiv:proc
; entete
.model SMALL
.586
.CODE
debut:
STARTUPCODE

; ouvrePrinc 4
mov bp,sp
sub sp,4

; ecrireChaine "c1="
.DATA
mess0 DB "c1=$"
.CODE
lea dx, mess0
push dx
call ecrch

; lireEnt -2
lea dx, [bp-2]
push dx
call lirent

; aLaLigne
call ligsuiv

; iconst
; iload -2
push word ptr[bp-2]
; ecrireEnt
call ecrent

; aLaLigne
call ligsuiv

; iconst 2
push word ptr 2

; iconst
; iload -2
push word ptr[bp-2]
; imul
pop bx
pop ax
imul bx
push ax

; iconst 3
push word ptr 3

; iconst 4
push word ptr 4

; imul
pop bx
pop ax
imul bx
push ax

; iadd
pop bx
pop ax
add ax,bx
push ax

; iconst 5
push word ptr 5

; isub
pop bx
pop ax
sub ax,bx
push ax

; ecrireEnt
call ecrent

; aLaLigne
call ligsuiv

; ecrireChaine "factorielle 5= "
.DATA
mess1 DB "factorielle 5= $"
.CODE
lea dx, mess1
push dx
call ecrch

; iconst 5
push word ptr 5

; iconst 4
push word ptr 4

; imul
pop bx
pop ax
imul bx
push ax

; iconst 3
push word ptr 3

; imul
pop bx
pop ax
imul bx
push ax

; iconst 2
push word ptr 2

; imul
pop bx
pop ax
imul bx
push ax

; iconst 1
push word ptr 1

; imul
pop bx
pop ax
imul bx
push ax

; ecrireEnt
call ecrent

; queue
nop
EXITCODE
End debut

