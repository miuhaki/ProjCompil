; entete 
extrn lirent:proc, ecrent:proc, ecrbool:proc, ecrch:proc, ligsuiv:proc 
.model SMALL 
.586 

.CODE 
debut : 
STARTUPCODE 


; ouvrePrinc 14
mov bp, sp 
sub sp, 14


; ecrireChaine "c1="
.DATA 
mess0 DB "c1=$"
.CODE 
lea dx, mess0
push dx 
call ecrch 


; lireEnt -2
lea dx,[bp-2] 
push dx 
call lirent 


; aLaLigne 
call ligsuiv 


; ecrireChaine "c2="
.DATA 
mess1 DB "c2=$"
.CODE 
lea dx, mess1
push dx 
call ecrch 


; lireEnt -4
lea dx,[bp-4] 
push dx 
call lirent 


; aLaLigne 
call ligsuiv 


; iconst 10
push word ptr 10


; iconst 10
push word ptr 10


; iconst 2
push word ptr 2


; idiv 
pop bx 
pop ax 
cwd 
idiv bx 
push ax 


; iadd 
pop bx 
pop ax 
add ax, bx 
push ax 


; iconst 5
push word ptr 5


; idiv 
pop bx 
pop ax 
cwd 
idiv bx 
push ax 


; ecrireEnt 
call ecrent 


; aLaLigne 
call ligsuiv 


; iload -2
push word ptr [bp-2] 


; iconst 3
push word ptr 3


; iload -2
push word ptr [bp-2] 


; imul 
pop bx 
pop ax 
imul bx 
push ax 


; iadd 
pop bx 
pop ax 
add ax, bx 
push ax 


; iconst 10
push word ptr 10


; isub 
pop bx 
pop ax 
sub ax, bx 
push ax 


; ecrireEnt 
call ecrent 


; aLaLigne 
call ligsuiv 


; iconst -1
push word ptr -1


; iconst -1
push word ptr -1


; iou 
 pop ax 
 mov bx, -1 
 cmp ax, bx 
je VRAI0 
pop ax 
cmp ax,bx 
je VRAI0 
push 0 
jmp FIN0 
VRAI0: 
push -1 
FIN0: 


; ecrireBool 
call ecrent 


; aLaLigne 
call ligsuiv 


; iload -2
push word ptr [bp-2] 


; iload -4
push word ptr [bp-4] 


; iconst 4
push word ptr 4


; iadd 
pop bx 
pop ax 
add ax, bx 
push ax 


; iinfegal 
pop bx 
pop ax 
cmp ax, bx 
jg $+6 
push -1 
jmp $+4 
push 0 


; ecrireBool 
call ecrent 


; aLaLigne 
call ligsuiv 


; queue 
nop 
EXITCODE 
End debut 


