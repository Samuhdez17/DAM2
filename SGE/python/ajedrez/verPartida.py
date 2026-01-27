import tkinter as tk
from tkinter import filedialog, messagebox
import chess

# =============================
# CONFIGURACIÓN DEL TABLERO
# =============================
TAM = 60
COLOR_CLARO = "#f0d9b5"
COLOR_OSCURO = "#b58863"

PIEZAS = {
    'r': '♜', 'n': '♞', 'b': '♝', 'q': '♛', 'k': '♚', 'p': '♟',
    'R': '♖', 'N': '♘', 'B': '♗', 'Q': '♕', 'K': '♔', 'P': '♙'
}

# =============================
# CLASE PRINCIPAL
# =============================
class VisorAjedrez:
    def __init__(self, root):
        self.root = root
        self.root.title("Visor de partidas de ajedrez")

        self.board = chess.Board()
        self.movimientos = []
        self.indice = 0
        self.delay = tk.IntVar(value=0)

        self.crear_ui()
        self.dibujar_tablero()

    # -------------------------
    def crear_ui(self):
        self.canvas = tk.Canvas(self.root, width=8*TAM, height=8*TAM)
        self.canvas.grid(row=0, column=0, rowspan=8)

        tk.Label(self.root, text="Segundos entre movimientos").grid(row=0, column=1)
        tk.Entry(self.root, textvariable=self.delay).grid(row=1, column=1)

        tk.Button(self.root, text="Cargar fichero", command=self.cargar_fichero).grid(row=2, column=1, sticky="ew")
        tk.Button(self.root, text="Detener", command=self.detener).grid(row=3, column=1, sticky="ew")
        self.btn_avanzar = tk.Button(self.root, text="Avanzar", command=self.avanzar)
        self.btn_avanzar.grid(row=4, column=1, sticky="ew")
        self.btn_retroceder = tk.Button(self.root, text="Retroceder", command=self.retroceder)
        self.btn_retroceder.grid(row=5, column=1, sticky="ew")

    # -------------------------
    def dibujar_tablero(self):
        self.canvas.delete("all")

        for fila in range(8):
            for col in range(8):
                color = COLOR_CLARO if (fila + col) % 2 == 0 else COLOR_OSCURO
                self.canvas.create_rectangle(
                    col*TAM, fila*TAM,
                    (col+1)*TAM, (fila+1)*TAM,
                    fill=color
                )

        for square in chess.SQUARES:
            pieza = self.board.piece_at(square)
            if pieza:
                col = chess.square_file(square)
                fila = 7 - chess.square_rank(square)
                self.canvas.create_text(
                    col*TAM + TAM//2,
                    fila*TAM + TAM//2,
                    text=PIEZAS[pieza.symbol()],
                    font=("Arial", 32)
                )

    # -------------------------
    def cargar_fichero(self):
        ruta = filedialog.askopenfilename(filetypes=[("Textos", "*.txt")])
        if not ruta:
            return

        with open(ruta, 'r', encoding='utf-8') as f:
            self.movimientos = [line.strip() for line in f if line.strip()]

        self.board.reset()
        self.indice = 0
        self.dibujar_tablero()
        self.reproducir()

    # -------------------------
    def reproducir(self):
        if self.delay.get() > 0 and self.indice < len(self.movimientos):
            self.avanzar()
            self.root.after(self.delay.get() * 1000, self.reproducir)

    # -------------------------
    def avanzar(self):
        if self.indice >= len(self.movimientos):
            return

        mov = chess.Move.from_uci(self.movimientos[self.indice])
        if mov in self.board.legal_moves:
            self.board.push(mov)
            self.indice += 1
            self.dibujar_tablero()
        else:
            messagebox.showerror("Error", f"Movimiento ilegal: {mov}")

    # -------------------------
    def retroceder(self):
        if self.indice > 0:
            self.board.pop()
            self.indice -= 1
            self.dibujar_tablero()

    # -------------------------
    def detener(self):
        self.delay.set(0)

# =============================
# MAIN
# =============================
if __name__ == "__main__":
    root = tk.Tk()
    app = VisorAjedrez(root)
    root.mainloop()
