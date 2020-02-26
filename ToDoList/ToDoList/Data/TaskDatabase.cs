using System.Collections.Generic;
using System.Threading.Tasks;
using SQLite;
using ToDoList.Models;

namespace ToDoList.Data
{
    public class TaskDatabase
    {
        readonly SQLiteAsyncConnection _database;
        public TaskDatabase(string dbPath)
        {
            _database = new SQLiteAsyncConnection(dbPath);
            _database.CreateTableAsync<Models.Task>().Wait();
        }

        public Task<List<Models.Task>> GetNotesAsync()
        {
            return _database.Table<Models.Task>().ToListAsync();
        }

        public Task<Models.Task> GetNoteAsync(int id)
        {
            return _database.Table<Models.Task>()
                            .Where(i => i.ID == id)
                            .FirstOrDefaultAsync();
        }

        public Task<int> SaveNoteAsync(Models.Task task)
        {
            if (task.ID != 0)
            {
                return _database.UpdateAsync(task);
            }
            else
            {
                return _database.InsertAsync(task);
            }
        }

        public Task<int> DeleteNoteAsync(Models.Task task)
        {
            return _database.DeleteAsync(task);
        }
    }
}
