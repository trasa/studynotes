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
        private readonly ContainerColumn[] columns = new[]
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

        /// <summary>
        /// Add a piece to the game board in the column given.
        /// 
        /// Returns True if the addition of this piece puts the 
        /// game in a 'winning' configuration for the player.
        /// </summary>
        /// <param name="color">piece that we are adding</param>
        /// <param name="column">column to add the piece to</param>
        public bool AddPiece(PieceColor color, int column)
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
            int row = col.AddPiece(color);

            return new WinDetector(this, color, column, row).IsWinner;
        }

        public bool IsColumnFull(int column)
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

        public PieceColor GetPiece(int column, int row)
        {
            VerifyColumn(column);
            
            return columns[column].GetPiece(row);
        }

        private void VerifyColumn(int column)
        {
            if (column < 0 || column >= columns.Length)
            {
                throw new ArgumentOutOfRangeException("column", column, "Must be between 0 and " + (columns.Length - 1));
            }
        }

        /// <summary>
        /// Count up the number of pieces to the left of this given 
        /// spot that are the same color as the given spot.
        /// 
        /// Note that this does not count the space that we start 
        /// on.  So the boundary conditions will return 0.
        /// </summary>
        /// <param name="color">color we're looking for</param>
        /// <param name="column">column to start looking at</param>
        /// <param name="row">the row we're checking out</param>
        /// <returns>number of pieces to the left of start that are the same color</returns>
        public int GetPieceCountLeft(PieceColor color, int column, int row)
        {
            int columnToCheck = column - 1;
            if (columnToCheck < 0 || columns[columnToCheck].GetPiece(row) != color)
            {
                // either we hit the end or we hit a piece that isn't the right color.
                return 0;
            }
            return 1 + GetPieceCountLeft(color, columnToCheck, row);
        }

        /// <summary>
        /// Count up the number of pieces to the right of this spot
        /// that are the same color as the given spot.
        /// 
        /// Note that this doesn't count the space that we start
        /// on.  So the boundary conditions will return 0.
        /// </summary>
        /// <param name="color">color we're looking for</param>
        /// <param name="column">column to start looking at</param>
        /// <param name="row">the row we're checking out</param>
        /// <returns>number of pieces to the right of start that are the same color</returns>
        public int GetPieceCountRight(PieceColor color, int column, int row)
        {
            int columnToCheck = column + 1;
            if (columnToCheck == columns.Length || columns[columnToCheck].GetPiece(row) != color)
            {
                // the end, or the wrong piece.
                return 0;
            }
            return 1 + GetPieceCountRight(color, columnToCheck, row);
        }

        /// <summary>
        /// Count the number of pieces above this spot that are the same
        /// color as the given spot.
        /// </summary>
        /// <param name="color">color we're looking for</param>
        /// <param name="column">column we're checking out</param>
        /// <param name="row">row to start looking at</param>
        /// <returns>number of pieces above here that are the same color</returns>
        public int GetPieceCountUp(PieceColor color, int column, int row)
        {
            int rowToCheck = row + 1;
            if (rowToCheck == ContainerColumn.MaxHeight || columns[column].GetPiece(rowToCheck) != color)
            {
                // the top or the wrong piece
                return 0;
            }
            return 1 + GetPieceCountUp(color, column, rowToCheck);
        }

        /// <summary>
        /// Count the number of pieces below this spot that are the same
        /// color as the given spot.
        /// </summary>
        /// <param name="color">color we're looking for</param>
        /// <param name="column">column we're checking out</param>
        /// <param name="row">row to start looking at</param>
        /// <returns>number of pieces below here that are the same color</returns>
        public int GetPieceCountDown(PieceColor color, int column, int row)
        {
            int rowToCheck = row - 1;
            if (rowToCheck < 0 || columns[column].GetPiece(rowToCheck) != color)
            {
                // the bottom or the wrong piece
                return 0;
            }
            return 1 + GetPieceCountDown(color, column, rowToCheck);
        }

        /// <summary>
        /// Count the number of pieces "up and to the right" from this spot
        /// that are the same color as the given spot.
        /// </summary>
        /// <param name="color">color we're looking for</param>
        /// <param name="column">start column to check out</param>
        /// <param name="row">start row to look at</param>
        /// <returns>number of matching pieces</returns>
        public int GetPieceCountPositiveUp(PieceColor color, int column, int row)
        {
            int rowToCheck = row + 1;
            int columnToCheck = column + 1;

            if (rowToCheck == ContainerColumn.MaxHeight ||
                columnToCheck == ColumnCount ||
                columns[columnToCheck].GetPiece(rowToCheck) != color)
            {
                // not it.
                return 0;
            }
            return 1 + GetPieceCountPositiveUp(color, columnToCheck, rowToCheck);
        }

        /// <summary>
        /// Count the number of pieces "down and to the left" from this spot
        /// that are the same color as the given spot.
        /// </summary>
        /// <param name="color">color we're looking for</param>
        /// <param name="column">start column to check out</param>
        /// <param name="row">start row to look at</param>
        /// <returns>number of matching pieces</returns>
        public int GetPieceCountPositiveDown(PieceColor color, int column, int row)
        {
            int rowToCheck = row - 1;
            int columnToCheck = column - 1;

            if (rowToCheck < 0 ||
                columnToCheck < 0 ||
                columns[columnToCheck].GetPiece(rowToCheck) != color)
            {
                // not it.
                return 0;
            }
            return 1 + GetPieceCountPositiveDown(color, columnToCheck, rowToCheck);
        }

        /// <summary>
        /// Count the number of pieces "up and to the left" from this spot
        /// that are the same color as the given spot.
        /// </summary>
        /// <param name="color">color we're looking for</param>
        /// <param name="column">start column to check out</param>
        /// <param name="row">start row to look at</param>
        /// <returns>number of matching pieces</returns>
        public int GetPieceCountNegativeUp(PieceColor color, int column, int row)
        {
            int rowToCheck = row + 1;
            int columnToCheck = column - 1;

            if (rowToCheck == ContainerColumn.MaxHeight ||
                columnToCheck < 0 ||
                columns[columnToCheck].GetPiece(rowToCheck) != color)
            {
                // not it.
                return 0;
            }
            return 1 + GetPieceCountNegativeUp(color, columnToCheck, rowToCheck);
        }


        /// <summary>
        /// Count the number of pieces "down and to the right" from this spot
        /// that are the same color as the given spot.
        /// </summary>
        /// <param name="color">color we're looking for</param>
        /// <param name="column">start column to check out</param>
        /// <param name="row">start row to look at</param>
        /// <returns>number of matching pieces</returns>
        public int GetPieceCountNegativeDown(PieceColor color, int column, int row)
        {
            int rowToCheck = row - 1;
            int columnToCheck = column + 1;

            if (rowToCheck < 0 ||
                columnToCheck == ColumnCount ||
                columns[columnToCheck].GetPiece(rowToCheck) != color)
            {
                // not it.
                return 0;
            }
            return 1 + GetPieceCountNegativeDown(color, columnToCheck, rowToCheck);
        }
    }
}
