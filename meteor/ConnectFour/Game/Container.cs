using System;
using System.Linq;

namespace ConnectFour.Game
{
    /// <summary>
    /// The main game board of a ConnectFour game.
    /// 
    /// The Container has 7 columns, each are 6 spaces deep.
    /// The board is arranged such:
    ///     0 1 2 3 4 5 6  (columns)
    ///   5 . . . . . . . 
    ///   4 . . . . . . . 
    ///   3 . . . . . . . 
    ///   2 . . . . . . . 
    ///   1 . . . . . . . 
    ///   0 . . . . . . . 
    /// (rows)
    /// 
    /// So that when a piece is dropped into a column, it falls 
    /// to the lowest numbered row available.
    /// </summary>
    public class Container
    {
        // the columns of the game board:
        private readonly ContainerColumn[] columns = new ContainerColumn[]
            {
                new ContainerColumn(), 
                new ContainerColumn(), 
                new ContainerColumn(), 
                new ContainerColumn(), 
                new ContainerColumn(), 
                new ContainerColumn(),
                new ContainerColumn()
            };

        /// <summary>
        /// The number of columns in the container.
        /// </summary>
        public int ColumnCount { get { return columns.Length; } }

        public void AddPiece(PieceColor color, uint column)
        {
            VerifyColumn(column);
            if (color == PieceColor.None)
            {
                throw new IllegalPlacementException("Can't put a 'none' piece in the container.");
            }
            ContainerColumn col = columns[column];
            if (col.IsFull)
            {
                throw new IllegalPlacementException("Can't put a piece here, this column is full.");
            }
            col.AddPiece(color);
        }

        public bool IsColumnFull(uint column)
        {
            VerifyColumn(column);
            return columns[column].IsFull;
        }

        public bool IsContainerFull
        {
            get
            {
                return columns.All(c => c.IsFull);
            }
        }

        public PieceColor GetPiece(uint column, uint row)
        {
            VerifyColumn(column);
            
            return columns[column].GetPiece(row);
        }

        private void VerifyColumn(uint column)
        {
            if (column >= columns.Length)
            {
                throw new ArgumentOutOfRangeException("column", column, "Must be between 0 and " + columns.Length);
            }
        }
    }
}
